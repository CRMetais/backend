package school.sptech.cr_metais.controller;

import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.repository.FornecedorRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/nota-fiscal")
public class NotaFiscalController {

    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository produtoRepository;

    public NotaFiscalController(FornecedorRepository fornecedorRepository,
                                ProdutoRepository produtoRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Map<String, Object> gerarNotaFiscal(@RequestBody Map<String, Object> boleta) {

        Integer idFornecedor = Integer.parseInt(boleta.get("idFornecedor").toString());

        Fornecedor f = fornecedorRepository.findById(idFornecedor)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        Map<String, Object> nota = new HashMap<>();

        nota.put("dataEmissao", LocalDate.now().toString());
        nota.put("tipoNota", boleta.get("tipoNota"));

        Map<String, Object> fornecedor = new HashMap<>();
        fornecedor.put("id", f.getIdFornecedor());
        fornecedor.put("nome", f.getNome());
        fornecedor.put("documento", f.getDocumento());

        Map<String, Object> endereco = new HashMap<>();
        endereco.put("logradouro", f.getEndereco().getLogradouro());
        endereco.put("numero", f.getEndereco().getNumero());
        endereco.put("bairro", f.getEndereco().getBairro());
        endereco.put("cidade", f.getEndereco().getCidade());
        endereco.put("cep", f.getEndereco().getCep());
        endereco.put("estado", f.getEndereco().getEstado());

        fornecedor.put("endereco", endereco);

        nota.put("fornecedor", fornecedor);

        List<Map<String, Object>> itensBoleta =
                (List<Map<String, Object>>) boleta.get("itens");

        List<Map<String, Object>> itens = new ArrayList<>();

        double totalNota = 0;

        for (Map<String, Object> itemBoleta : itensBoleta) {

            Integer idProduto = Integer.parseInt(itemBoleta.get("produtoId").toString());

            Produto p = produtoRepository.findById(idProduto)
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            Map<String, Object> item = new HashMap<>();

            item.put("idProduto", p.getIdProduto());
            item.put("descricao", p.getNome());

            item.put("quantidade", Double.parseDouble(itemBoleta.get("peso").toString()));
            item.put("valorUnitario", Double.parseDouble(itemBoleta.get("valorUnitario").toString()));
            item.put("total", Double.parseDouble(itemBoleta.get("total").toString()));

            totalNota += Double.parseDouble(itemBoleta.get("total").toString());

            itens.add(item);
        }

        nota.put("itens", itens);
        nota.put("valorTotal", totalNota);

        return nota;
    }
}