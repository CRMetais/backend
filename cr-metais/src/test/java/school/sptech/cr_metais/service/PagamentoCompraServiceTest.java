package school.sptech.cr_metais.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.cr_metais.dto.PagamentoCompra.PagamentoCompraCadastroDto;
import school.sptech.cr_metais.dto.PagamentoCompra.PagamentoCompraResponseDto;
import school.sptech.cr_metais.entity.Compra;
import school.sptech.cr_metais.entity.ContaPagamento;
import school.sptech.cr_metais.entity.PagamentoCompra;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.PagamentoCompraMapper;
import school.sptech.cr_metais.repository.CompraRepository;
import school.sptech.cr_metais.repository.ContaPagamentoRepository;
import school.sptech.cr_metais.repository.PagamentoCompraRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PagamentoCompraServiceTest {


    @InjectMocks
    PagamentoCompraService service;

    @Mock
    PagamentoCompraRepository repository;

    @Mock
    CompraRepository compraRepository;

    @Mock
    ContaPagamentoRepository contaPagamentoRepository;

    @Mock
    PagamentoCompraMapper mapper;

    @Test
    @DisplayName("Deve retornar lista vazia")
    void deveRetornarListaVaziaTest() {

        List<PagamentoCompra> lista = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(lista);

        List<PagamentoCompraResponseDto> recebido = service.listar();

        assertNotNull(recebido);
        assertTrue(recebido.isEmpty());
    }

    @Test
    @DisplayName("Deve lançar erro ao cadastrar quando compra não existe")
    void deveLancarErroCadastrarCompraInexistenteTest() {

        PagamentoCompraCadastroDto dto = new PagamentoCompraCadastroDto();
        dto.setIdCompra(1);
        dto.setIdContaPagamento(10);

        Mockito.when(compraRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class,
                () -> service.cadastrar(dto));
    }

    @Test
    @DisplayName("Deve lançar erro ao cadastrar quando conta pagamento não existe")
    void deveLancarErroCadastrarContaInexistenteTest() {

        PagamentoCompraCadastroDto dto = new PagamentoCompraCadastroDto();
        dto.setIdCompra(1);
        dto.setIdContaPagamento(10);

        Compra compra = new Compra();
        compra.setIdCompra(1);

        Mockito.when(compraRepository.findById(1)).thenReturn(Optional.of(compra));
        Mockito.when(contaPagamentoRepository.findById(10)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class,
                () -> service.cadastrar(dto));
    }

    @Test
    @DisplayName("Deve lançar erro ao buscar por ID inexistente")
    void deveLancarErroBuscarPorIdTest() {

        Mockito.when(repository.findById(5)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class,
                () -> service.buscarPorId(5));
    }

    @Test
    @DisplayName("Deve deletar pagamento")
    void deveDeletarTest() {

        Mockito.when(repository.existsById(3)).thenReturn(true);

        assertDoesNotThrow(() -> service.deletar(3));
        Mockito.verify(repository).deleteById(3);
    }

    @Test
    @DisplayName("Deve lançar erro ao deletar ID inexistente")
    void deveLancarErroDeletarTest() {

        Mockito.when(repository.existsById(3)).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class,
                () -> service.deletar(3));
    }
}
