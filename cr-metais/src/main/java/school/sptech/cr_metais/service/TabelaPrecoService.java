package school.sptech.cr_metais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.TabelaPreco.TabelaPrecoCadastroDTO;
import school.sptech.cr_metais.dto.TabelaPreco.TabelaPrecoResponseDTO;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.TabelaPrecoMapper;
import school.sptech.cr_metais.repository.TabelaPrecoRepository;

import java.util.List;

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

    public List<TabelaPrecoResponseDTO> listar() {
        return tRepository.findAll()
                .stream()
                .map(tabelaPrecoMapper::toDTO)
                .toList();
    }

    public TabelaPrecoResponseDTO buscarPorId(Integer id) {
        TabelaPreco tabelaProcurada = tRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tabela não encontrada"));

        return tabelaPrecoMapper.toDTO(tabelaProcurada);
    }

    public void deletar(Integer id) {
        if (!tRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Tabela não encontrada");
        }
        tRepository.deleteById(id);
    }

    public TabelaPrecoResponseDTO atualizar(Integer id, TabelaPrecoCadastroDTO dto) {

        TabelaPreco tabelaParaAtualizar = tRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tabela não encontrada"));

        tabelaParaAtualizar.setVersao(dto.getVersao());
        tabelaParaAtualizar.setNomeTabela(dto.getNomeTabela());
        tabelaParaAtualizar.setAtiva(dto.getAtiva());
        tabelaParaAtualizar.setTipo(dto.getTipo());
        tabelaParaAtualizar.setDataFimValidade(dto.getDataFimValidade());
        tabelaParaAtualizar.setDataInicioValidade(dto.getDataInicioValidade());
        TabelaPreco tabelaAtualizada = tRepository.save(tabelaParaAtualizar);

        return tabelaPrecoMapper.toDTO(tabelaAtualizada);
    }
}
