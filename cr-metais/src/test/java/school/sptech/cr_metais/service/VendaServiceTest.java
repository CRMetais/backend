package school.sptech.cr_metais.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.cr_metais.dto.Venda.VendaCadastroDTO;
import school.sptech.cr_metais.dto.Venda.VendaResponseDTO;
import school.sptech.cr_metais.entity.Cliente;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.entity.Venda;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.VendaMapper;
import school.sptech.cr_metais.repository.ClienteRepository;
import school.sptech.cr_metais.repository.TabelaPrecoRepository;
import school.sptech.cr_metais.repository.VendaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VendaServiceTest {

    @InjectMocks
    private VendaService vendaService;

    @Mock
    private VendaMapper vendaMapper;

    @Mock
    private VendaRepository vRepository;

    @Mock
    private ClienteRepository clienteRepository;


    @Nested
    @DisplayName("Método Cadastrar")
    class Cadastrar {

        @Test
        @DisplayName("Ao passar as devidas informações deve cadastrar com sucesso")
        void deveCadastrarComSucesso() {

            Cliente cliente = new Cliente();
            cliente.setIdCliente(1);

            TabelaPreco tabelaPreco = new TabelaPreco();
            tabelaPreco.setIdTabela(10);

            VendaCadastroDTO dto = new VendaCadastroDTO();
            dto.setIdCliente(1);
            dto.setDatavenda(LocalDate.of(2024, 11, 20));

            Venda vendaMapeada = new Venda();
            vendaMapeada.setDataVenda(dto.getDatavenda());

            Venda vendaSalva = new Venda();
            vendaSalva.setIdVenda(1);
            vendaSalva.setFkCliente(cliente);
            vendaSalva.setDataVenda(dto.getDatavenda());

            Mockito.when(vendaMapper.toEntity(dto))
                    .thenReturn(vendaMapeada);

            Mockito.when(clienteRepository.findById(dto.getIdCliente()))
                    .thenReturn(Optional.of(cliente));

            Mockito.when(vRepository.save(Mockito.any(Venda.class)))
                    .thenReturn(vendaSalva);

            Venda resultado = vendaService.cadastrar(dto);

            assertNotNull(resultado);
            assertEquals(1, resultado.getIdVenda());
            assertEquals(cliente, resultado.getFkCliente());
            assertEquals(dto.getDatavenda(), resultado.getDataVenda());

            Mockito.verify(vRepository, Mockito.times(1))
                    .save(Mockito.any(Venda.class));
        }
    }
    @Nested
    @DisplayName("Método Listar")
    class MetodoListar {

        @Test
        @DisplayName("Deve retornar lista com itens")
        void deveRetornarListaComItens() {

            List<Venda> listaMockada = List.of(new Venda(), new Venda());

            Mockito.when(vRepository.findAll()).thenReturn(listaMockada);

            Mockito.when(vendaMapper.toResponseDTO(Mockito.any(Venda.class)))
                    .thenReturn(new VendaResponseDTO());

            List<VendaResponseDTO> resultado = vendaService.listar();

            assertEquals(2, resultado.size());
        }

        @Test
        @DisplayName("Deve retornar lista vazia quando não houver itens")
        void deveRetornarListaVazia() {

            Mockito.when(vRepository.findAll()).thenReturn(List.of());

            List<VendaResponseDTO> resultado = vendaService.listar();

            assertTrue(resultado.isEmpty());
        }
    }

    @Nested
    @DisplayName("Método buscarPorId")
    class MetodoBuscarPorId {

        @Test
        @DisplayName("Deve retornar item quando ele existe")
        void deveRetornarItemQuandoExiste() {

            Venda venda = new Venda();
            venda.setIdVenda(5);
            venda.setDataVenda(LocalDate.of(2024, 11, 20));

            VendaResponseDTO dto = new VendaResponseDTO(
                    5,
                    new Cliente(),
                    LocalDate.of(2024, 11, 20)
            );

            Mockito.when(vRepository.findById(5))
                    .thenReturn(Optional.of(venda));

            Mockito.when(vendaMapper.toResponseDTO(venda))
                    .thenReturn(dto);

            VendaResponseDTO resultado = vendaService.buscarPorId(5);

            assertNotNull(resultado);
            assertEquals(5, resultado.getIdVenda());
        }

        @Test
        @DisplayName("Deve lançar exceção se o item não existir")
        void deveLancarExcessaoSeItemNaoExistir() {

            Mockito.when(vRepository.findById(200)).thenReturn(Optional.empty());

            EntidadeNaoEncontradaException exception = assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> vendaService.buscarPorId(200),
                    "Deveria lançar exceção se o item não existir"
            );

            assertEquals("Venda não encontrado", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Método deletar")
    class MetodoDeletar {

        @Test
        @DisplayName("Deve deletar quando o item existe")
        void deveDeletarQuandoItemExiste() {

            Mockito.when(vRepository.existsById(7)).thenReturn(true);

            vendaService.deletar(7);

            Mockito.verify(vRepository).deleteById(7);
        }

        @Test
        @DisplayName("Deve lançar exceção quando tenta deletar item que não existe")
        void deveLancarExcessaoQuandoNaoExiste() {

            Mockito.when(vRepository.existsById(77)).thenReturn(false);

            EntidadeNaoEncontradaException exception = assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> vendaService.deletar(77),
                    "Deveria lançar exceção se o item não existir"
            );

            assertEquals("Venda não encontrada", exception.getMessage());
        }
    }
}