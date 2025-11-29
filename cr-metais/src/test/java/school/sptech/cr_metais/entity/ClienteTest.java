package school.sptech.cr_metais.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.cr_metais.dto.Cliente.ClienteCadastroDTO;
import school.sptech.cr_metais.dto.Cliente.ClienteResponseDTO;
import school.sptech.cr_metais.exception.EntidadeInvalidaException;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.ClienteMapper;
import school.sptech.cr_metais.repository.ClienteRepository;
import school.sptech.cr_metais.repository.EnderecoRepository;
import school.sptech.cr_metais.repository.TabelaPrecoRepository;
import school.sptech.cr_metais.service.ClienteService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClienteTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteMapper clienteMapper;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private TabelaPrecoRepository tabelaPrecoRepository;

    @Nested
    class Cadastrar {

        @Test
        @DisplayName("Ao passar as devidas informações deve cadastrar com sucesso")
        void deveCadastrarComSucesso() {

            Endereco endereco = new Endereco();
            TabelaPreco tabelaPreco = new TabelaPreco();

            ClienteCadastroDTO dto = new ClienteCadastroDTO();
            dto.setCnpj("12.345.678/0001-10");
            dto.setRazaoSocial("Empresa Teste");
            dto.setTelContato("11999999999");
            dto.setIdEndereco(123);
            dto.setIdTabelaPreco(999);

            Cliente clienteMapeado = new Cliente();
            clienteMapeado.setCnpj(dto.getCnpj());
            clienteMapeado.setRazaoSocial(dto.getRazaoSocial());
            clienteMapeado.setTelContato(dto.getTelContato());

            Cliente clienteSalvo = new Cliente();
            clienteSalvo.setIdCliente(1);
            clienteSalvo.setCnpj(dto.getCnpj());
            clienteSalvo.setRazaoSocial(dto.getRazaoSocial());
            clienteSalvo.setTelContato(dto.getTelContato());
            clienteSalvo.setEndereco(endereco);
            clienteSalvo.setTabelaPreco(tabelaPreco);

            Mockito.when(clienteMapper.toEntity(dto))
                    .thenReturn(clienteMapeado);

            Mockito.when(enderecoRepository.findById(Mockito.any()))
                    .thenReturn(Optional.of(endereco));

            Mockito.when(tabelaPrecoRepository.findById(Mockito.any()))
                    .thenReturn(Optional.of(tabelaPreco));

            Mockito.when(clienteRepository.save(Mockito.any()))
                    .thenReturn(clienteSalvo);

            Cliente resultado = clienteService.cadastrar(dto);

            assertNotNull(resultado);
            assertEquals(1, resultado.getIdCliente());
            assertEquals(dto.getCnpj(), resultado.getCnpj());
            assertEquals(dto.getRazaoSocial(), resultado.getRazaoSocial());
            assertEquals(dto.getTelContato(), resultado.getTelContato());
            assertEquals(endereco, resultado.getEndereco());
            assertEquals(tabelaPreco, resultado.getTabelaPreco());

            Mockito.verify(clienteRepository, Mockito.times(1))
                    .save(Mockito.any());
        }

        @Test
        @DisplayName("Deve falhar quando endereço não existe")
        void deveFalharQuandoEnderecoNaoExiste() {

            ClienteCadastroDTO dto = new ClienteCadastroDTO();
            dto.setCnpj("12.345.678/0001-10");
            dto.setRazaoSocial("Empresa Teste");
            dto.setTelContato("11999999999");
            dto.setIdEndereco(50);
            dto.setIdTabelaPreco(10);

            Cliente clienteMapeado = new Cliente();
            Mockito.when(clienteMapper.toEntity(dto))
                    .thenReturn(clienteMapeado);

            Mockito.when(enderecoRepository.findById(50))
                    .thenReturn(Optional.empty());

            Mockito.lenient()
                    .when(tabelaPrecoRepository.findById(Mockito.any()))
                    .thenReturn(Optional.of(new TabelaPreco()));

            assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> clienteService.cadastrar(dto)
            );
        }

        // ---------------------------------------------------------
        // 2. TABELA DE PREÇO NÃO ENCONTRADA
        // ---------------------------------------------------------
        @Test
        @DisplayName("Quando a tabela de preço não existir deve lançar EntidadeNaoEncontradaException")
        void deveFalharQuandoTabelaPrecoNaoExiste() {

            ClienteCadastroDTO dto = new ClienteCadastroDTO();
            dto.setCnpj("12.345.678/0001-10");
            dto.setRazaoSocial("Empresa Teste");
            dto.setTelContato("11999999999");
            dto.setIdEndereco(123);
            dto.setIdTabelaPreco(999);

            Mockito.when(clienteMapper.toEntity(dto))
                    .thenReturn(new Cliente());

            Mockito.when(enderecoRepository.findById(Mockito.any()))
                    .thenReturn(Optional.of(new Endereco()));

            // Tabela preço não encontrada
            Mockito.when(tabelaPrecoRepository.findById(Mockito.any()))
                    .thenReturn(Optional.empty());

            assertThrows(EntidadeNaoEncontradaException.class,
                    () -> clienteService.cadastrar(dto)
            );
        }

        // ---------------------------------------------------------
        // 3. CNPJ INVÁLIDO
        // ---------------------------------------------------------
        @Test
        @DisplayName("Deve falhar quando CNPJ é inválido")
        void deveFalharQuandoCnpjInvalido() {

            ClienteCadastroDTO dto = new ClienteCadastroDTO();
            dto.setCnpj("CNPJ_INVALIDO");
            dto.setRazaoSocial("Empresa X");
            dto.setTelContato("11999999999");
            dto.setIdEndereco(10);
            dto.setIdTabelaPreco(20);

            assertThrows(
                    EntidadeInvalidaException.class,
                    () -> clienteService.cadastrar(dto)
            );
        }

        @Nested
        @DisplayName("Método Listar")
        class MetodoListar{

            @Test
            @DisplayName("Deve retornar lista com itens")
            void deveRetornarListaComItens(){

                List<Cliente> listaMockada = List.of(new Cliente(), new Cliente());
                Mockito.when(clienteRepository.findAll()).thenReturn(listaMockada);

                List<ClienteResponseDTO> resultado = clienteService.listar();

                assertEquals(2, resultado.size());
            }

            @Test
            @DisplayName("Deve retornar lista vazia quando não houver itens")
            void deveRetornarListaVazia(){

                Mockito.when(clienteRepository.findAll()).thenReturn(List.of());

                List<ClienteResponseDTO> resultado = clienteService.listar();

                assertTrue(resultado.isEmpty());
            }
        }

        @Nested
        @DisplayName("Método buscarPorId")
        class MetodoBuscarPorId{

            @Test
            @DisplayName("Deve retornar item quando ele existe")
            void deveRetornarItemQuandoExiste(){

                Cliente cliente = new Cliente();
                cliente.setIdCliente(5);

                ClienteResponseDTO dto = new ClienteResponseDTO(
                        5,
                        "Empresa Teste",
                        "12.345.678/0001-10",
                        "11999999999"
                );

                Mockito.when(clienteRepository.findById(5))
                        .thenReturn(Optional.of(cliente));

                Mockito.when(clienteMapper.toResponseDTO(cliente))
                        .thenReturn(dto);

                ClienteResponseDTO resultado = clienteService.buscarPorId(5);

                assertNotNull(resultado);
                assertEquals(5, resultado.getIdCliente());
            }


            @Test
            @DisplayName("Deve lançar exceção se o item não existir")
            void deveLancarExcessaoSeItemNaoExistir(){

                Mockito.when(clienteRepository.findById(200)).thenReturn(Optional.empty());

                EntidadeNaoEncontradaException exception = assertThrows(
                        EntidadeNaoEncontradaException.class,
                        () -> clienteService.buscarPorId(200),
                        "Deveria lançar exceção se o item não existir"
                );

                assertEquals("Cliente não encontrado", exception.getMessage());
            }
        }


        @Test
        @DisplayName("Deve lançar exceção se o item não existir")
        void deveLancarExcessaoSeItemNaoExistir(){

            Mockito.when(clienteRepository.findById(200)).thenReturn(Optional.empty());

            EntidadeNaoEncontradaException exception = assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> clienteService.buscarPorId(200),
                    "Deveria lançar exceção se o item não existir"
            );
            assertEquals("Cliente não encontrado", exception.getMessage());
        }

    }

    @Nested
    @DisplayName("Método deletarPorId")
    class MetodoDeletarPorId{

        @Test
        @DisplayName("Deve deletar quando o item existe")
        void deveDeletarQuandoItemExiste(){

            Mockito.when(clienteRepository.existsById(7)).thenReturn(true);

            clienteService.deletar(7);

            Mockito.verify(clienteRepository).deleteById(7);
        }

        @Test
        @DisplayName("Deve lançar exceção quando tenta deletar item que não existe")
        void deveLancarExcessaoQuandoNaoExiste(){

            Mockito.when(clienteRepository.existsById(77)).thenReturn(false);

            EntidadeNaoEncontradaException exception = assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> clienteService.deletar(77),
                    "Deveria lançar exceção se o item não existir"
            );

            assertEquals("Cliente não encontrado", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Método atualizar")
    class MetodoAtualizar{

        @Test
        @DisplayName("Deve atualizar quando o cliente existe")
        void deveAtualizarQuandoExiste() {

            // id que será atualizado
            int id = 21;

            // DTO com os novos dados
            ClienteCadastroDTO dto = new ClienteCadastroDTO();
            dto.setCnpj("12.345.678/0001-10");
            dto.setRazaoSocial("Empresa Atualizada");
            dto.setTelContato("11999999999");

            // Objeto existente no banco
            Cliente clienteExistente = new Cliente();
            clienteExistente.setIdCliente(id);
            clienteExistente.setCnpj("00.000.000/0001-00");
            clienteExistente.setRazaoSocial("Empresa Antiga");
            clienteExistente.setTelContato("1122223333");

            ClienteResponseDTO dtoEsperado = new ClienteResponseDTO(
                    id,
                    dto.getCnpj(),
                    dto.getRazaoSocial(),
                    dto.getTelContato()
            );


            Mockito.when(clienteRepository.findById(id))
                    .thenReturn(Optional.of(clienteExistente));

            Mockito.when(clienteRepository.save(clienteExistente))
                    .thenReturn(clienteExistente);

            Mockito.when(clienteMapper.toResponseDTO(clienteExistente))
                    .thenReturn(dtoEsperado);

            ClienteResponseDTO resposta = clienteService.atualizar(id, dto);

            assertEquals("Empresa Atualizada", resposta.getRazaoSocial());
            assertEquals("12.345.678/0001-10", resposta.getCnpj());
            assertEquals("11999999999", resposta.getTelContato());
        }






        @Test
        @DisplayName("Deve lançar exceção quando o cliente não existe")
        void deveLancarExcessaoQuandoNaoExiste() {

            int id = 99;

            ClienteCadastroDTO dto = new ClienteCadastroDTO();
            dto.setCnpj("11.111.111/0001-11");
            dto.setRazaoSocial("Test");
            dto.setTelContato("11999999999");

            Mockito.when(clienteRepository.findById(id))
                    .thenReturn(Optional.empty());

            EntidadeNaoEncontradaException ex = assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> clienteService.atualizar(id, dto)
            );

            assertEquals("Cliente não encontrado", ex.getMessage());
        }


    }
}


