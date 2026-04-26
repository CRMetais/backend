package school.sptech.cr_metais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.TabelaPreco.FornecedorTabelaPrecoDto;
import school.sptech.cr_metais.dto.TabelaPreco.TabelaPrecoCadastroDTO;
import school.sptech.cr_metais.dto.TabelaPreco.TabelaPrecoDto;
import school.sptech.cr_metais.dto.TabelaPreco.TabelaPrecoResponseDTO;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.entity.TipoTabela;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.TabelaPrecoMapper;
import school.sptech.cr_metais.repository.TabelaPrecoRepository;

import java.time.LocalDate;
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

    public List<TabelaPrecoDto> buscarPrecosporNomeTabela(String nome) {
        List<Object[]> resultado = tRepository.buscarPorNomeTabela(nome);
        List<TabelaPrecoDto> response = new ArrayList<>();

        for (Object[] linha : resultado) {
            // Adiciona esses logs para debugar
            System.out.println("linha[0] = " + linha[0] + " | tipo: " + (linha[0] != null ? linha[0].getClass() : "null"));
            System.out.println("linha[1] = " + linha[1] + " | tipo: " + (linha[1] != null ? linha[1].getClass() : "null"));
            System.out.println("linha[2] = " + linha[2] + " | tipo: " + (linha[2] != null ? linha[2].getClass() : "null"));
            System.out.println("linha[3] = " + linha[3] + " | tipo: " + (linha[3] != null ? linha[3].getClass() : "null"));

            String nomeProduto = (String) linha[0];
            LocalDate data     = linha[1] != null ? ((java.sql.Date) linha[1]).toLocalDate() : null;
            Double versao      = linha[2] != null ? ((Number) linha[2]).doubleValue() : null;
            Double preco       = linha[3] != null ? ((Number) linha[3]).doubleValue() : null;

            response.add(new TabelaPrecoDto(nomeProduto, data, preco, versao));
        }

        return response;
    }

    public TabelaPrecoResponseDTO cadastrarTabelaVenda(String nomeTabela) {
        TabelaPreco nova = new TabelaPreco();
        nova.setNomeTabela(nomeTabela.toUpperCase()); // padroniza maiúsculo
        nova.setTipo(TipoTabela.V);
        nova.setVersao(1.0);
        nova.setDataInicioValidade(LocalDate.now());
        nova.setDataFimValidade(null);
        nova.setAtiva(true);  // ou (byte)1 dependendo do tipo do campo

        TabelaPreco salva = tRepository.save(nova);
        return tabelaPrecoMapper.toDTO(salva);
    }

    public List<TabelaPrecoResponseDTO> listarVenda() {
        return tRepository.findAllVenda()
                .stream()
                .map(tabelaPrecoMapper::toDTO)
                .toList();
    }


    public List<TabelaPrecoResponseDTO> listarCompra() {
        return tRepository.findAllCompra()
                .stream()
                .map(tabelaPrecoMapper::toDTO)
                .toList();
    }

    public TabelaPrecoResponseDTO cadastrarTabelaCompra(String nomeTabela) {
        TabelaPreco nova = new TabelaPreco();
        nova.setNomeTabela(nomeTabela.toUpperCase());
        nova.setTipo(TipoTabela.C);          // ← C em vez de V
        nova.setVersao(1.0);
        nova.setDataInicioValidade(LocalDate.now());
        nova.setDataFimValidade(null);
        nova.setAtiva(true);
        return tabelaPrecoMapper.toDTO(tRepository.save(nova));
    }


}
