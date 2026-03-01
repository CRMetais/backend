package school.sptech.cr_metais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.TabelaPreco.FornecedorTabelaPrecoDto;
import school.sptech.cr_metais.dto.TabelaPreco.TabelaPrecoCadastroDTO;
import school.sptech.cr_metais.dto.TabelaPreco.TabelaPrecoResponseDTO;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.TabelaPrecoMapper;
import school.sptech.cr_metais.repository.TabelaPrecoRepository;

import java.util.ArrayList;
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

    public List<FornecedorTabelaPrecoDto> listarFornecedoresComTabelaPreco() {
        List<Object[]> resultado = tRepository.listarFornecedoresComTabelaPreco();
        List<FornecedorTabelaPrecoDto> response = new ArrayList<>();

        for (Object[] linha : resultado) {
            FornecedorTabelaPrecoDto dto = new FornecedorTabelaPrecoDto();
            dto.setIdFornecedor(((Number) linha[0]).intValue());
            dto.setNome((String) linha[1]);
            dto.setApelido((String) linha[2]);
            dto.setDocumento((String) linha[3]);
            dto.setTelefone((String) linha[4]);
            dto.setFkTabelaPreco(linha[5] == null ? null : ((Number) linha[5]).intValue());
            dto.setNomeTabela((String) linha[6]);
            response.add(dto);
        }

        return response;
    }
}
