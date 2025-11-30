package school.sptech.cr_metais.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.cr_metais.dto.TabelaPreco.TabelaPrecoCadastroDTO;
import school.sptech.cr_metais.dto.TabelaPreco.TabelaPrecoResponseDTO;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.entity.TipoTabela; // enum com C e V
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.TabelaPrecoMapper;
import school.sptech.cr_metais.repository.TabelaPrecoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes unitários do TabelaPrecoService")
class TabelaPrecoServiceTest {

    @InjectMocks
    private TabelaPrecoService tabelaPrecoService;

    @Mock
    private TabelaPrecoRepository tabelaPrecoRepository;

    @Mock
    private TabelaPrecoMapper tabelaPrecoMapper;

    @Test
    @DisplayName("Deve cadastrar tabela com datas válidas e retornar DTO de resposta")
    void cadastrarQuandoDatasValidasDeveSalvarETrazerDtoTest() {
        TabelaPrecoCadastroDTO dto = new TabelaPrecoCadastroDTO();
        dto.setNomeTabela("Tabela A");
        dto.setVersao(1.0); // Double
        dto.setTipo(TipoTabela.C); // C = compra (por exemplo)
        dto.setAtiva(true);
        dto.setDataInicioValidade(LocalDate.of(2025, 1, 1));
        dto.setDataFimValidade(LocalDate.of(2025, 12, 31));

        TabelaPreco entidade = new TabelaPreco();
        entidade.setIdTabela(1);
        entidade.setNomeTabela("Tabela A");
        entidade.setVersao(1.0);
        entidade.setTipo(TipoTabela.C);
        entidade.setAtiva(true);
        entidade.setDataInicioValidade(dto.getDataInicioValidade());
        entidade.setDataFimValidade(dto.getDataFimValidade());

        TabelaPrecoResponseDTO respostaEsperada = new TabelaPrecoResponseDTO();
        respostaEsperada.setIdTabela(1);
        respostaEsperada.setNomeTabela("Tabela A");
        respostaEsperada.setVersao(1.0);
        respostaEsperada.setAtiva(true);

        Mockito.when(tabelaPrecoMapper.toEntity(dto)).thenReturn(entidade);
        Mockito.when(tabelaPrecoRepository.save(entidade)).thenReturn(entidade);
        Mockito.when(tabelaPrecoMapper.toDTO(entidade)).thenReturn(respostaEsperada);

        TabelaPrecoResponseDTO recebido = tabelaPrecoService.cadastrar(dto);

        Assertions.assertNotNull(recebido);
        Assertions.assertEquals(respostaEsperada.getIdTabela(), recebido.getIdTabela());
        Assertions.assertEquals("Tabela A", recebido.getNomeTabela());
        Assertions.assertEquals(1.0, recebido.getVersao());
        Mockito.verify(tabelaPrecoRepository, Mockito.times(1)).save(entidade);
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando data fim for antes da data início no cadastro")
    void cadastrarQuandoDataFimAntesDeInicioDeveLancarIllegalArgumentExceptionTest() {
        TabelaPrecoCadastroDTO dto = new TabelaPrecoCadastroDTO();
        dto.setDataInicioValidade(LocalDate.of(2025, 10, 10));
        dto.setDataFimValidade(LocalDate.of(2025, 10, 1));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tabelaPrecoService.cadastrar(dto));
    }

    @Test
    @DisplayName("Deve retornar lista vazia ao listar quando não houver tabelas")
    void listarDeveRetornarListaVaziaTest() {
        List<TabelaPreco> entidades = new ArrayList<>();
        Mockito.when(tabelaPrecoRepository.findAll()).thenReturn(entidades);

        List<TabelaPrecoResponseDTO> recebido = tabelaPrecoService.listar();

        Assertions.assertNotNull(recebido);
        Assertions.assertTrue(recebido.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar lista com três DTOs ao listar tabelas")
    void listarDeveRetornarTresTabelasTest() {
        TabelaPreco t1 = new TabelaPreco();
        t1.setIdTabela(1);
        TabelaPreco t2 = new TabelaPreco();
        t2.setIdTabela(2);
        TabelaPreco t3 = new TabelaPreco();
        t3.setIdTabela(3);

        List<TabelaPreco> entidades = List.of(t1, t2, t3);

        TabelaPrecoResponseDTO dto1 = new TabelaPrecoResponseDTO();
        dto1.setIdTabela(1);
        TabelaPrecoResponseDTO dto2 = new TabelaPrecoResponseDTO();
        dto2.setIdTabela(2);
        TabelaPrecoResponseDTO dto3 = new TabelaPrecoResponseDTO();
        dto3.setIdTabela(3);

        Mockito.when(tabelaPrecoRepository.findAll()).thenReturn(entidades);
        Mockito.when(tabelaPrecoMapper.toDTO(t1)).thenReturn(dto1);
        Mockito.when(tabelaPrecoMapper.toDTO(t2)).thenReturn(dto2);
        Mockito.when(tabelaPrecoMapper.toDTO(t3)).thenReturn(dto3);

        List<TabelaPrecoResponseDTO> recebido = tabelaPrecoService.listar();

        Assertions.assertEquals(3, recebido.size());
        Assertions.assertEquals(1, recebido.get(0).getIdTabela());
        Assertions.assertEquals(2, recebido.get(1).getIdTabela());
        Assertions.assertEquals(3, recebido.get(2).getIdTabela());
    }

    @Test
    @DisplayName("Deve retornar DTO ao buscar tabela por ID válido")
    void buscarPorIdQuandoIdValidoDeveRetornarDtoTest() {
        TabelaPreco entidade = new TabelaPreco();
        entidade.setIdTabela(10);
        entidade.setNomeTabela("Tabela Especial");
        entidade.setVersao(2.5);

        TabelaPrecoResponseDTO dto = new TabelaPrecoResponseDTO();
        dto.setIdTabela(10);
        dto.setNomeTabela("Tabela Especial");
        dto.setVersao(2.5);

        Mockito.when(tabelaPrecoRepository.findById(10)).thenReturn(Optional.of(entidade));
        Mockito.when(tabelaPrecoMapper.toDTO(entidade)).thenReturn(dto);

        TabelaPrecoResponseDTO recebido = tabelaPrecoService.buscarPorId(10);

        Assertions.assertNotNull(recebido);
        Assertions.assertEquals("Tabela Especial", recebido.getNomeTabela());
        Assertions.assertEquals(2.5, recebido.getVersao());
    }

    @Test
    @DisplayName("Deve lançar EntidadeNaoEncontradaException ao buscar por ID inexistente")
    void buscarPorIdQuandoIdInvalidoDeveLancarEntidadeNaoEncontradaExceptionTest() {
        Mockito.when(tabelaPrecoRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(EntidadeNaoEncontradaException.class,
                () -> tabelaPrecoService.buscarPorId(99));
    }

    @Test
    @DisplayName("Deve deletar tabela quando ID for válido")
    void deletarQuandoIdValidoDeveExecutarDeleteTest() {
        Mockito.when(tabelaPrecoRepository.existsById(5)).thenReturn(true);

        tabelaPrecoService.deletar(5);

        Mockito.verify(tabelaPrecoRepository, Mockito.times(1)).deleteById(5);
    }

    @Test
    @DisplayName("Deve lançar EntidadeNaoEncontradaException ao tentar deletar tabela inexistente")
    void deletarQuandoIdInvalidoDeveLancarEntidadeNaoEncontradaExceptionTest() {
        Mockito.when(tabelaPrecoRepository.existsById(5)).thenReturn(false);

        Assertions.assertThrows(EntidadeNaoEncontradaException.class,
                () -> tabelaPrecoService.deletar(5));
    }

    @Test
    @DisplayName("Deve atualizar tabela existente com sucesso")
    void atualizarQuandoIdValidoDeveAtualizarCamposESalvarTest() {
        Integer id = 7;

        TabelaPreco entidadeExistente = new TabelaPreco();
        entidadeExistente.setIdTabela(id);
        entidadeExistente.setNomeTabela("Tabela Antiga");
        entidadeExistente.setVersao(1.0);
        entidadeExistente.setAtiva(false);
        entidadeExistente.setTipo(TipoTabela.C);

        TabelaPrecoCadastroDTO dtoAtualizacao = new TabelaPrecoCadastroDTO();
        dtoAtualizacao.setNomeTabela("Tabela Atualizada");
        dtoAtualizacao.setVersao(2.0);
        dtoAtualizacao.setAtiva(true);
        dtoAtualizacao.setTipo(TipoTabela.V); // V = venda (por exemplo)
        dtoAtualizacao.setDataInicioValidade(LocalDate.of(2025, 1, 1));
        dtoAtualizacao.setDataFimValidade(LocalDate.of(2025, 12, 31));

        TabelaPreco entidadeAtualizada = new TabelaPreco();
        entidadeAtualizada.setIdTabela(id);
        entidadeAtualizada.setNomeTabela(dtoAtualizacao.getNomeTabela());
        entidadeAtualizada.setVersao(dtoAtualizacao.getVersao());
        entidadeAtualizada.setAtiva(dtoAtualizacao.getAtiva());
        entidadeAtualizada.setTipo(dtoAtualizacao.getTipo());
        entidadeAtualizada.setDataInicioValidade(dtoAtualizacao.getDataInicioValidade());
        entidadeAtualizada.setDataFimValidade(dtoAtualizacao.getDataFimValidade());

        TabelaPrecoResponseDTO dtoResposta = new TabelaPrecoResponseDTO();
        dtoResposta.setIdTabela(id);
        dtoResposta.setNomeTabela("Tabela Atualizada");
        dtoResposta.setVersao(2.0);
        dtoResposta.setAtiva(true);

        Mockito.when(tabelaPrecoRepository.findById(id)).thenReturn(Optional.of(entidadeExistente));
        Mockito.when(tabelaPrecoRepository.save(entidadeExistente)).thenReturn(entidadeAtualizada);
        Mockito.when(tabelaPrecoMapper.toDTO(entidadeAtualizada)).thenReturn(dtoResposta);

        TabelaPrecoResponseDTO recebido = tabelaPrecoService.atualizar(id, dtoAtualizacao);

        Assertions.assertNotNull(recebido);
        Assertions.assertEquals("Tabela Atualizada", recebido.getNomeTabela());
        Assertions.assertEquals(2.0, recebido.getVersao());
        Assertions.assertTrue(recebido.getAtiva());
    }

    @Test
    @DisplayName("Deve lançar EntidadeNaoEncontradaException ao atualizar tabela inexistente")
    void atualizarQuandoIdInvalidoDeveLancarEntidadeNaoEncontradaExceptionTest() {
        Mockito.when(tabelaPrecoRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        TabelaPrecoCadastroDTO dto = new TabelaPrecoCadastroDTO();
        dto.setNomeTabela("Alguma Tabela");
        dto.setVersao(1.0);

        Assertions.assertThrows(EntidadeNaoEncontradaException.class,
                () -> tabelaPrecoService.atualizar(123, dto));
    }
}
