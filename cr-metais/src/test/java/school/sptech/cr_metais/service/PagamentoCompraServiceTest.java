package school.sptech.cr_metais.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.cr_metais.dto.PagamentoCompra.PagamentoCompraCadastroDto;
import school.sptech.cr_metais.entity.Compra;
import school.sptech.cr_metais.entity.ContaPagamento;
import school.sptech.cr_metais.entity.PagamentoCompra;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.PagamentoCompraMapper;
import school.sptech.cr_metais.repository.CompraRepository;
import school.sptech.cr_metais.repository.ContaPagamentoRepository;
import school.sptech.cr_metais.repository.PagamentoCompraRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PagamentoCompraServiceTest {

    @InjectMocks
    PagamentoCompraService pagamentoCompraService;

    @Mock
    CompraRepository compraRepository;

    @Mock
    ContaPagamentoRepository contaPagamentoRepository;

    @Mock
    PagamentoCompraRepository pagamentoCompraRepository;

    @Mock
    PagamentoCompraMapper pagamentoCompraMapper;

//    @Test
//    @DisplayName("Deve cadastrar PagamentoCompra com sucesso")
//    void deveCadastrarComSucesso() {
//
//        PagamentoCompraCadastroDto dto = new PagamentoCompraCadastroDto();
//        dto.setIdCompra(10);
//        dto.setIdContaPagamento(20);
//
//        PagamentoCompra pagamentoMock = new PagamentoCompra();
//        Compra compraMock = new Compra();
//        ContaPagamento contaMock = new ContaPagamento();
//
//        Mockito.when(pagamentoCompraMapper.toEntity(dto)).thenReturn(pagamentoMock);
//        Mockito.when(compraRepository.findById(10)).thenReturn(Optional.of(compraMock));
//        Mockito.when(contaPagamentoRepository.findById(20)).thenReturn(Optional.of(contaMock));
//        Mockito.when(pagamentoCompraRepository.save(pagamentoMock)).thenReturn(pagamentoMock);
//
//        PagamentoCompra recebido = pagamentoCompraService.cadastrar(dto);
//
//        assertNotNull(recebido);
//        assertEquals(compraMock, recebido.getCompra());
//        assertEquals(contaMock, recebido.getContaPagamento());
//
//        Mockito.verify(pagamentoCompraRepository, Mockito.times(1)).save(pagamentoMock);
//    }

    @Test
    @DisplayName("Deve lançar erro quando compra não existe")
    void deveLancarErroCompraNaoEncontrada() {

        PagamentoCompraCadastroDto dto = new PagamentoCompraCadastroDto();
        dto.setIdCompra(99); // inexistente

        Mockito.when(compraRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> pagamentoCompraService.cadastrar(dto)
        );
    }


    @Test
    @DisplayName("Deve lançar erro quando conta pagamento não existe")
    void deveLancarErroContaPagamentoNaoEncontrada() {

        PagamentoCompraCadastroDto dto = new PagamentoCompraCadastroDto();
        dto.setIdCompra(10);
        dto.setIdContaPagamento(99);

        Mockito.when(compraRepository.findById(10))
                .thenReturn(Optional.of(new Compra()));

        Mockito.when(contaPagamentoRepository.findById(99))
                .thenReturn(Optional.empty());

        assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> pagamentoCompraService.cadastrar(dto)
        );
    }

    @Test
    @DisplayName("Deve listar vazio")
    void deveListarVazio() {

        Mockito.when(pagamentoCompraRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<PagamentoCompra> lista = pagamentoCompraService.listar();

        assertNotNull(lista);
        assertTrue(lista.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar exatamente 3 pagamentos")
    void deveListarTresPagamentos() {

        PagamentoCompra p1 = new PagamentoCompra(1, new Compra(), LocalDate.now(), new ContaPagamento());
        PagamentoCompra p2 = new PagamentoCompra(2, new Compra(), LocalDate.now(), new ContaPagamento());
        PagamentoCompra p3 = new PagamentoCompra(3, new Compra(), LocalDate.now(), new ContaPagamento());

        List<PagamentoCompra> mockList = List.of(p1, p2, p3);

        Mockito.when(pagamentoCompraRepository.findAll()).thenReturn(mockList);

        List<PagamentoCompra> recebido = pagamentoCompraService.listar();

        assertEquals(3, recebido.size());
        assertTrue(recebido.contains(p1));
        assertTrue(recebido.contains(p2));
        assertTrue(recebido.contains(p3));
    }
}
