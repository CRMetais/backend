package school.sptech.cr_metais.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.cr_metais.dto.Compra.CompraCadastroDto;
import school.sptech.cr_metais.dto.Compra.CompraResponseDto;
import school.sptech.cr_metais.entity.Compra;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.mappers.CompraMapper;
import school.sptech.cr_metais.repository.CompraRepository;
import school.sptech.cr_metais.repository.FornecedorRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CompraServiceTest {

    @Mock
    private CompraMapper compraMapper;

    @Mock
    private CompraRepository compraRepository;

    @Mock
    private FornecedorRepository fornecedorRepository;

    @InjectMocks
    private CompraService compraService;

    @Test
    @DisplayName("Deve retornar uma lista vazia")
    void deveRetornarListaVaziaTest(){

        List<Compra> compras = new ArrayList<>();
        Mockito.when(compraRepository.findAll()).thenReturn(compras);

        List<Compra> recebido = compraService.listar();
        Assertions.assertNotNull(recebido);

    }

    @Test
    @DisplayName("Deve listar compras com sucesso")
    void deveListarCompras() {

        Fornecedor fornecedor = new Fornecedor();
        Compra compra = new Compra(1, fornecedor, LocalDate.now());

        List<Compra> listaMock = List.of(compra);

        Mockito.when(compraRepository.findAll()).thenReturn(listaMock);

        List<Compra> recebido = compraService.listar();

        assertEquals(1, recebido.size());
        assertEquals(compra, recebido.get(0));
    }

//    @Test
//    @DisplayName("Deve cadastrar compra com sucesso")
//    void deveCadastrarCompra() {
//
//        CompraCadastroDto dto = new CompraCadastroDto();
//        dto.setIdFornecedor(1);
//
//        Compra compraEntity = new Compra();
//        Fornecedor fornecedor = new Fornecedor();
//
//        Mockito.when(compraMapper.toEntity(dto)).thenReturn(compraEntity);
//        Mockito.when(fornecedorRepository.findById(1)).thenReturn(Optional.of(fornecedor));
//        Mockito.when(compraRepository.save(compraEntity)).thenReturn(compraEntity);
//
//        Compra recebido = compraService.cadastrar(dto);
//
//        assertNotNull(recebido);
//        assertEquals(fornecedor, recebido.getFornecedor());
//        Mockito.verify(compraRepository, Mockito.times(1)).save(compraEntity);
//    }

    @Test
    @DisplayName("Deve retornar exatamente 3 compras")
    void deveRetornarTresCompras() {

        Fornecedor fornecedor = new Fornecedor();

        Compra c1 = new Compra(1, fornecedor, LocalDate.now());
        Compra c2 = new Compra(2, fornecedor, LocalDate.now());
        Compra c3 = new Compra(3, fornecedor, LocalDate.now());

        List<Compra> comprasMock = List.of(c1, c2, c3);

        Mockito.when(compraRepository.findAll()).thenReturn(comprasMock);

        List<Compra> recebido = compraService.listar();

        assertNotNull(recebido);
        assertEquals(3, recebido.size());
        assertTrue(recebido.contains(c1));
        assertTrue(recebido.contains(c2));
        assertTrue(recebido.contains(c3));
    }

}


