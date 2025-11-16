package school.sptech.cr_metais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.TabelaPreco.TabelaPrecoCadastroDTO;
import school.sptech.cr_metais.dto.TabelaPreco.TabelaPrecoResponseDTO;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.mappers.TabelaPrecoMapper;
import school.sptech.cr_metais.repository.TabelaPrecoRepository;

@Service
public class TabelaPrecoService {

    @Autowired
    private final TabelaPrecoRepository tRepository;
    private final TabelaPrecoMapper tabelaPrecoMapper;

    public TabelaPrecoService(TabelaPrecoRepository tRepository, TabelaPrecoMapper tabelaPrecoMapper) {
        this.tRepository = tRepository;
        this.tabelaPrecoMapper = tabelaPrecoMapper;

    }

    public TabelaPrecoResponseDTO cadastrar(TabelaPrecoCadastroDTO dto) {

        if (dto.getDataInicioValidade() != null
                && dto.getDataFimValidade().isBefore(dto.getDataInicioValidade())) {

            throw new IllegalArgumentException("Data fim não pode ser antes da data início.");
        }

        TabelaPreco tabelaCadastro = tabelaPrecoMapper.toEntity(dto);

        tRepository.save(tabelaCadastro);

        return tabelaPrecoMapper.toDTO(tabelaCadastro);
    }
}
