package school.sptech.cr_metais.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.repository.FornecedorRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/nota-fiscal")
public class NotaFiscalController {

    private final FornecedorRepository fornecedorRepository;

    public NotaFiscalController(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @GetMapping("/{idFornecedor}")
    public Map<String, Object> gerarNotaFiscal(@PathVariable Integer idFornecedor){

        Fornecedor f = fornecedorRepository.findById(idFornecedor)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Fornecedor não encontrado"));

        Map<String, Object> nota = new HashMap<>();

        nota.put("nome", f.getNome());
        nota.put("documento", f.getDocumento());

        Map<String, Object> endereco = new HashMap<>();
        endereco.put("logradouro", f.getEndereco().getLogradouro());
        endereco.put("numero", f.getEndereco().getNumero());
        endereco.put("bairro", f.getEndereco().getBairro());
        endereco.put("cidade", f.getEndereco().getCidade());
        endereco.put("cep", f.getEndereco().getCep());
        endereco.put("estado", f.getEndereco().getEstado());

        nota.put("endereco", endereco);

        return nota;

    }
}
