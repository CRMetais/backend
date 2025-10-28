package school.sptech.cr_metais.service.strategy;

import school.sptech.cr_metais.dto.FornecedorCadastroDTO;
import school.sptech.cr_metais.entity.TipoFornecedor;

public interface ValidacaoCadastroFornecedorStrategy {

    /**
     * @param dto
     * @throws school.sptech.cr_metais.exception.EntidadeConflitoException
     */
    void validarConflitos(FornecedorCadastroDTO dto);

    /**
     * @return
     */
    TipoFornecedor getTipo();
}