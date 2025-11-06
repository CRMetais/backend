package school.sptech.cr_metais.service.strategy;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.Fornecedor.FornecedorCadastroDto;
import school.sptech.cr_metais.entity.TipoFornecedor;
import school.sptech.cr_metais.exception.EntidadeConflitoException;
import school.sptech.cr_metais.repository.FornecedorRepository;

@Component
public class ValidacaoPessoaFisicaStrategy implements ValidacaoCadastroFornecedorStrategy {

    private final FornecedorRepository fRepository;

    public ValidacaoPessoaFisicaStrategy(FornecedorRepository fRepository) {
        this.fRepository = fRepository;
    }

    @Override
    public void validarConflitos(FornecedorCadastroDto dto) {
        if (fRepository.existsByDocumento(dto.getDocumento())) {
            throw new EntidadeConflitoException("Conflito no campo CPF");
        }
    }

    @Override
    public TipoFornecedor getTipo() {
        return TipoFornecedor.PESSOA_FISICA;
    }
}