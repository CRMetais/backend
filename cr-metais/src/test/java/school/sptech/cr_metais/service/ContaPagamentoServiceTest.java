package school.sptech.cr_metais.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.cr_metais.dto.ContaPagamento.ContaPagamentoResponseDto;
import school.sptech.cr_metais.entity.ContaPagamento;
import school.sptech.cr_metais.entity.TipoConta;
import school.sptech.cr_metais.mappers.ContaPagamentoMapper;
import school.sptech.cr_metais.repository.ContaPagamentoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ContaPagamentoServiceTest {

    @InjectMocks
    private ContaPagamentoService contaPagamentoService;

    @Mock
    private ContaPagamentoRepository contaPagamentoRepository;

    @Mock
    private ContaPagamentoMapper contaPagamentoMapper;

    @Test
    void deveRetornarListaVaziaTeste() {
        List<ContaPagamento> contas = new ArrayList<>();
        Mockito.when(contaPagamentoRepository.findAll()).thenReturn(contas);

        List<ContaPagamentoResponseDto> recebido = contaPagamentoService.listar();
        Assertions.assertNotNull(recebido);
    }

    @Test
    void deveRetornarDoisObjetosTeste() {
        List<ContaPagamento> contasEntity = new ArrayList<>();
        
        ContaPagamento conta1 = new ContaPagamento();
        conta1.setIdContaPagamento(1);
        conta1.setPix(true);
        conta1.setChavePix("chave@teste.com");
        conta1.setNome("Conta 1");
        conta1.setContaAtiva(true);
        conta1.setPertenceFornecedor(true);
        contasEntity.add(conta1);
        
        ContaPagamento conta2 = new ContaPagamento();
        conta2.setIdContaPagamento(2);
        conta2.setPix(false);
        conta2.setBanco("Banco do Brasil");
        conta2.setAgencia("1234");
        conta2.setConta("567890");
        conta2.setTipoConta(TipoConta.C);
        conta2.setNome("Conta 2");
        conta2.setContaAtiva(true);
        conta2.setPertenceFornecedor(false);
        contasEntity.add(conta2);
        
        List<ContaPagamentoResponseDto> esperado = new ArrayList<>();
        ContaPagamentoResponseDto dto1 = new ContaPagamentoResponseDto();
        dto1.setIdContaPagamento(1);
        dto1.setNome("Conta 1");
        esperado.add(dto1);
        
        ContaPagamentoResponseDto dto2 = new ContaPagamentoResponseDto();
        dto2.setIdContaPagamento(2);
        dto2.setNome("Conta 2");
        esperado.add(dto2);
        
        Mockito.when(contaPagamentoRepository.findAll()).thenReturn(contasEntity);
        Mockito.when(contaPagamentoMapper.toResponseDTO(conta1)).thenReturn(dto1);
        Mockito.when(contaPagamentoMapper.toResponseDTO(conta2)).thenReturn(dto2);

        List<ContaPagamentoResponseDto> recebido = contaPagamentoService.listar();
        Assertions.assertArrayEquals(esperado.toArray(), recebido.toArray());
    }

    @Test
    void buscarPorIdQuandoAcionadoComIdValidoDeveRetornarContaPagamentoTest() {
        ContaPagamento conta = new ContaPagamento();
        conta.setIdContaPagamento(1);
        conta.setPix(true);
        conta.setChavePix("chave@teste.com");
        conta.setNome("Conta Teste");
        conta.setContaAtiva(true);
        conta.setPertenceFornecedor(true);

        ContaPagamentoResponseDto dto = new ContaPagamentoResponseDto();
        dto.setIdContaPagamento(1);
        dto.setNome("Conta Teste");

        Mockito.when(contaPagamentoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(conta));
        Mockito.when(contaPagamentoMapper.toResponseDTO(conta)).thenReturn(dto);

        ContaPagamentoResponseDto resultado = contaPagamentoService.buscarPorId(1);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(dto.getNome(), resultado.getNome());
    }

    @Test
    void deveRetornarListaComTresObjetosTeste() {
        List<ContaPagamento> contasEntity = new ArrayList<>();
        
        ContaPagamento conta1 = new ContaPagamento();
        conta1.setIdContaPagamento(1);
        conta1.setPix(true);
        conta1.setChavePix("chave1@teste.com");
        conta1.setNome("Conta 1");
        conta1.setContaAtiva(true);
        conta1.setPertenceFornecedor(true);
        contasEntity.add(conta1);
        
        ContaPagamento conta2 = new ContaPagamento();
        conta2.setIdContaPagamento(2);
        conta2.setPix(false);
        conta2.setBanco("Itau");
        conta2.setConta("111111");
        conta2.setNome("Conta 2");
        conta2.setContaAtiva(true);
        conta2.setPertenceFornecedor(false);
        contasEntity.add(conta2);
        
        ContaPagamento conta3 = new ContaPagamento();
        conta3.setIdContaPagamento(3);
        conta3.setPix(true);
        conta3.setChavePix("chave3@teste.com");
        conta3.setNome("Conta 3");
        conta3.setContaAtiva(false);
        conta3.setPertenceFornecedor(true);
        contasEntity.add(conta3);
        
        ContaPagamentoResponseDto dto1 = new ContaPagamentoResponseDto();
        dto1.setIdContaPagamento(1);
        ContaPagamentoResponseDto dto2 = new ContaPagamentoResponseDto();
        dto2.setIdContaPagamento(2);
        ContaPagamentoResponseDto dto3 = new ContaPagamentoResponseDto();
        dto3.setIdContaPagamento(3);
        
        Mockito.when(contaPagamentoRepository.findAll()).thenReturn(contasEntity);
        Mockito.when(contaPagamentoMapper.toResponseDTO(conta1)).thenReturn(dto1);
        Mockito.when(contaPagamentoMapper.toResponseDTO(conta2)).thenReturn(dto2);
        Mockito.when(contaPagamentoMapper.toResponseDTO(conta3)).thenReturn(dto3);

        List<ContaPagamentoResponseDto> recebido = contaPagamentoService.listar();
        Assertions.assertEquals(3, recebido.size());
    }

    @Test
    void buscarPorIdDeveRetornarContaComPixTest() {
        ContaPagamento conta = new ContaPagamento();
        conta.setIdContaPagamento(10);
        conta.setPix(true);
        conta.setChavePix("emailpix@teste.com");
        conta.setNome("Conta PIX Teste");
        conta.setContaAtiva(true);
        conta.setPertenceFornecedor(false);

        ContaPagamentoResponseDto dto = new ContaPagamentoResponseDto();
        dto.setIdContaPagamento(10);
        dto.setNome("Conta PIX Teste");
        dto.setPix(true);

        Mockito.when(contaPagamentoRepository.findById(10)).thenReturn(Optional.of(conta));
        Mockito.when(contaPagamentoMapper.toResponseDTO(conta)).thenReturn(dto);

        ContaPagamentoResponseDto resultado = contaPagamentoService.buscarPorId(10);
        Assertions.assertTrue(resultado.getPix());
    }

    @Test
    void deletarQuandoAcionadoComIdValidoDeveVerificarExistenciaTest() {
        Mockito.when(contaPagamentoRepository.existsById(20)).thenReturn(true);

        contaPagamentoService.deletar(20);

        Mockito.verify(contaPagamentoRepository, Mockito.times(1)).deleteById(20);
    }
}
