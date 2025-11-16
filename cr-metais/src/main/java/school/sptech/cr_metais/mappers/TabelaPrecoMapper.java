package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.TabelaPreco.TabelaPrecoCadastroDTO;
import school.sptech.cr_metais.dto.TabelaPreco.TabelaPrecoResponseDTO;
import school.sptech.cr_metais.entity.TabelaPreco;

@Component
public class TabelaPrecoMapper {

    public TabelaPreco toEntity(TabelaPrecoCadastroDTO dto) {
        TabelaPreco t = new TabelaPreco();
        t.setTipo(dto.getTipo());
        t.setNomeTabela(dto.getNomeTabela());
        t.setVersao(dto.getVersao());
        t.setDataInicioValidade(dto.getDataInicioValidade());
        t.setDataFimValidade(dto.getDataFimValidade());
        t.setAtiva(dto.getAtiva());
        return t;
    }

    public TabelaPrecoResponseDTO toDTO(TabelaPreco e) {
        return new TabelaPrecoResponseDTO(
                e.getIdTabela(),
                e.getNomeTabela(),
                e.getTipo(),
                e.getVersao(),
                e.getDataInicioValidade(),
                e.getDataFimValidade(),
                e.getAtiva()
        );
    }
}
