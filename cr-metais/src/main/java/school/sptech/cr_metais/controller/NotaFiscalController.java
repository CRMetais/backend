package school.sptech.cr_metais.controller;

import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.entity.Cliente;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.repository.ClienteRepository;
import school.sptech.cr_metais.repository.FornecedorRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/nota-fiscal")
public class NotaFiscalController {

    private final FornecedorRepository fornecedorRepository;
    private final ClienteRepository    clienteRepository;
    private final ProdutoRepository    produtoRepository;

    public NotaFiscalController(FornecedorRepository fornecedorRepository,
                                ClienteRepository clienteRepository,
                                ProdutoRepository produtoRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.clienteRepository    = clienteRepository;
        this.produtoRepository    = produtoRepository;
    }

    @PostMapping
    public Map<String, Object> gerarNotaFiscal(@RequestBody Map<String, Object> boleta) {

        String tipoNota = boleta.get("tipoNota").toString();

        String dataOperacao = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Map<String, Object> nota     = new HashMap<>();
        Map<String, Object> contato  = new HashMap<>();
        Map<String, Object> endereco = new HashMap<>();

        nota.put("dataEmissao", dataOperacao);
        nota.put("tipoNota", tipoNota);

        if ("ENTRADA".equals(tipoNota)) {
            // ── Fornecedor ────────────────────────────────────────────────
            Integer idFornecedor = Integer.parseInt(boleta.get("idFornecedor").toString());
            Fornecedor f = fornecedorRepository.findById(idFornecedor)
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

            contato.put("id",         f.getIdFornecedor());
            contato.put("nome",       f.getNome());
            contato.put("documento",  f.getDocumento());
            contato.put("telefone",   f.getTelefone() != null ? f.getTelefone() : "");
            contato.put("tipoPessoa", f.getTipoFornecedor().name().equals("PESSOA_JURIDICA") ? "J" : "F");
            contato.put("ie",         "");

            endereco.put("logradouro",  f.getEndereco().getLogradouro());
            endereco.put("numero",      f.getEndereco().getNumero());
            endereco.put("complemento", f.getEndereco().getComplemento() != null ? f.getEndereco().getComplemento() : "");
            endereco.put("bairro",      f.getEndereco().getBairro());
            endereco.put("cidade",      f.getEndereco().getCidade());
            endereco.put("cep",         f.getEndereco().getCep());
            endereco.put("estado",      f.getEndereco().getEstado());

        } else {
            // ── Cliente (sempre PJ com IE) ────────────────────────────────
            Integer idCliente = Integer.parseInt(boleta.get("idCliente").toString());
            Cliente c = clienteRepository.findByIdClienteAndAtivoTrue(idCliente)
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

            contato.put("id",         c.getIdCliente());
            contato.put("nome",       c.getRazaoSocial());
            contato.put("documento",  c.getCnpj());
            contato.put("telefone",   c.getTelContato() != null ? c.getTelContato() : "");
            contato.put("tipoPessoa", "J");
            contato.put("ie",         c.getIe() != null ? c.getIe() : "");

            endereco.put("logradouro",  c.getEndereco().getLogradouro());
            endereco.put("numero",      c.getEndereco().getNumero());
            endereco.put("complemento", c.getEndereco().getComplemento() != null ? c.getEndereco().getComplemento() : "");
            endereco.put("bairro",      c.getEndereco().getBairro());
            endereco.put("cidade",      c.getEndereco().getCidade());
            endereco.put("cep",         c.getEndereco().getCep());
            endereco.put("estado",      c.getEndereco().getEstado());
        }

        contato.put("endereco", endereco);
        nota.put("fornecedor", contato);

        // ── Itens ─────────────────────────────────────────────────────────
        List<Map<String, Object>> itensBoleta =
                (List<Map<String, Object>>) boleta.get("itens");

        List<Map<String, Object>> itens = new ArrayList<>();
        double totalNota = 0;

        for (Map<String, Object> itemBoleta : itensBoleta) {
            Integer idProduto = Integer.parseInt(itemBoleta.get("produtoId").toString());
            Produto p = produtoRepository.findById(idProduto)
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            Map<String, Object> item = new HashMap<>();
            item.put("idProduto",     p.getIdProduto());
            item.put("descricao",     p.getNome());
            item.put("unidade",       "KG");
            item.put("quantidade",    Double.parseDouble(itemBoleta.get("peso").toString()));
            item.put("valorUnitario", Double.parseDouble(itemBoleta.get("valorUnitario").toString()));
            item.put("total",         Double.parseDouble(itemBoleta.get("total").toString()));

            totalNota += Double.parseDouble(itemBoleta.get("total").toString());
            itens.add(item);
        }

        nota.put("itens", itens);
        nota.put("valorTotal", totalNota);

        return nota;
    }
}