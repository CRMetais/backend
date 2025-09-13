package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.exception.EntidadeConflitoException;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.repository.FornecedorRepository;

import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository fRepository;

    public FornecedorService(FornecedorRepository fRepository) {
        this.fRepository = fRepository;
    }

    public Fornecedor cadastrar(Fornecedor fornecedor){

        if (fRepository.existsByCpf(fornecedor.getCpf()) ){
            throw new EntidadeConflitoException("Conflito no campo CPF");
        }
        if (fRepository.existsByApelido(fornecedor.getApelido()) ){
            throw new EntidadeConflitoException("Conflito no campo Apelido");
        }

        return fRepository.save(fornecedor);
    }

    public List<Fornecedor> listar(){
        return fRepository.findAll();
    }

    public void deletar(Integer id){
        if (!fRepository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado");
        }
        fRepository.deleteById(id);
    }

    public Fornecedor buscarPorId(Integer id){
        return fRepository.findById(id)
                .orElseThrow(
                        ()-> new
                                EntidadeNaoEncontradaException
                                ("Fornecedor não encontrado")
                );
    }

    public Fornecedor atualizar(Integer id, Fornecedor fornecedor){
        Fornecedor fornecedorAtt = fRepository.findById(id)
                .orElseThrow(
                        ()-> new EntidadeNaoEncontradaException("Fornecedor não encontrado")
                );
        fornecedorAtt.setNome(fornecedor.getNome());
        fornecedorAtt.setCpf(fornecedor.getCpf());
        fornecedorAtt.setTelefone(fornecedor.getTelefone());
        fornecedorAtt.setApelido(fornecedor.getApelido());

        return fRepository.save(fornecedor);
    }
}
