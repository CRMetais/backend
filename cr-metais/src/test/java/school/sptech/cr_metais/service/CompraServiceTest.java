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
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
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
    @DisplayName("Deve retornar lista preenchida")
    void deveRetornarListaPreenchidaTest() {

        List<Compra> compras = List.of(new Compra());
        Mockito.when(compraRepository.findAll()).thenReturn(compras);

        List<CompraResponseDto> recebido = compraService.listar();

        Assertions.assertEquals(1, recebido.size());
    }

    @Test
    @DisplayName("Deve retornar lista vazia")
    void deveRetornarListaVaziaTest() {

        Mockito.when(compraRepository.findAll()).thenReturn(new ArrayList<>());

        List<CompraResponseDto> recebido = compraService.listar();

        Assertions.assertNotNull(recebido);
        Assertions.assertTrue(recebido.isEmpty());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar compras")
    void deveLancarExcecaoAoListarTest() {

        Mockito.when(compraRepository.findAll()).thenThrow(new RuntimeException("Erro ao consultar"));

        Assertions.assertThrows(RuntimeException.class, () -> compraService.listar());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar com fornecedor inexistente")
    void deveLancarExcecaoAoCadastrarFornecedorInexistenteTest() {

        CompraCadastroDto dto = new CompraCadastroDto();
        dto.setIdFornecedor(10);

        Mockito.when(fornecedorRepository.findById(10)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> compraService.cadastrar(dto)
        );
    }

    @Test
    @DisplayName("Deve deletar compra existente")
    void deveDeletarTest() {

        Mockito.when(compraRepository.existsById(1)).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> compraService.deletar(1));
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar compra inexistente")
    void deveLancarExcecaoAoDeletarInexistenteTest() {

        Mockito.when(compraRepository.existsById(1)).thenReturn(false);

        Assertions.assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> compraService.deletar(1)
        );
    }

    @Test
    @DisplayName("Deve atualizar compra")
    void deveAtualizarCompraTest() {

        CompraCadastroDto dto = new CompraCadastroDto();
        dto.setIdFornecedor(20);
        dto.setDataCompra(LocalDate.now());

        Fornecedor fornecedor = new Fornecedor();
        Compra compra = new Compra(1, fornecedor, LocalDate.now());

        Mockito.when(compraRepository.findById(1)).thenReturn(Optional.of(compra));
        Mockito.when(fornecedorRepository.findById(20)).thenReturn(Optional.of(fornecedor));
        Mockito.when(compraRepository.save(Mockito.any())).thenReturn(compra);

        CompraResponseDto recebido = compraService.atualizar(1, dto);

        Assertions.assertNotNull(recebido);
    }


    @Test
    @DisplayName("Deve lançar exceção ao atualizar compra inexistente")
    void deveLancarExcecaoAoAtualizarCompraInexistenteTest() {

        CompraCadastroDto dto = new CompraCadastroDto();
        dto.setIdFornecedor(20);

        Mockito.when(compraRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> compraService.atualizar(1, dto)
        );
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar com fornecedor inexistente")
    void deveLancarExcecaoFornecedorInexistenteTest() {

        CompraCadastroDto dto = new CompraCadastroDto();
        dto.setIdFornecedor(20);

        Compra compra = new Compra();
        Mockito.when(compraRepository.findById(1)).thenReturn(Optional.of(compra));
        Mockito.when(fornecedorRepository.findById(20)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> compraService.atualizar(1, dto)
        );
    }
}



