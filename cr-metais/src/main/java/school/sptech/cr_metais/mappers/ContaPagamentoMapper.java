package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.ContaPagamento.ContaPagamentoCadastroDto;
import school.sptech.cr_metais.dto.ContaPagamento.ContaPagamentoResponseDto;
import school.sptech.cr_metais.entity.ContaPagamento;
import school.sptech.cr_metais.entity.Fornecedor;

@Component
public class ContaPagamentoMapper {

    public ContaPagamento toEntity(ContaPagamentoCadastroDto dto) {
        ContaPagamento c = new ContaPagamento();
        c.setPix(dto.getPix());
        c.setBanco(dto.getBanco());
        c.setAgencia(dto.getAgencia());
        c.setConta(dto.getConta());
        c.setTipoConta(dto.getTipoConta());
        c.setChavePix(dto.getChavePix());
        c.setPertenceFornecedor(dto.getPertenceFornecedor());
        c.setNome(dto.getNome());
        c.setDocumento(dto.getDocumento());
        c.setContaAtiva(dto.getContaAtiva());
        return c;
    }

    public ContaPagamentoResponseDto toResponseDTO(ContaPagamento c) {
        return new ContaPagamentoResponseDto(
                c.getIdContaPagamento(),
                c.getPix(),
                c.getBanco(),
                c.getAgencia(),
                c.getConta(),
                c.getTipoConta(),
                c.getChavePix(),
                c.getPertenceFornecedor(),
                c.getNome(),
                c.getDocumento(),
                c.getContaAtiva(),
                c.getFornecedor()
        );
    }
}
