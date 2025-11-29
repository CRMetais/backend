package school.sptech.cr_metais.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.entity.TipoFornecedor;
import school.sptech.cr_metais.repository.FornecedorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FornecedorServiceTest {

    @InjectMocks
    private FornecedorService fornecedorService;

    @Mock
    private FornecedorRepository fornecedorRepository;

    @Test
    void deveRetornarListaVaziaTeste() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        Mockito.when(fornecedorRepository.findAll()).thenReturn(fornecedores);

        List<Fornecedor> recebido = fornecedorService.listar();
        Assertions.assertNotNull(recebido);
    }

    @Test
    void deveRetornarDoisObjetosTeste() {
        List<Fornecedor> esperado = new ArrayList<>();
        
        Fornecedor fornecedor1 = new Fornecedor();
        fornecedor1.setIdFornecedor(1);
        fornecedor1.setNome("Fornecedor A");
        fornecedor1.setDocumento("12345678901234");
        fornecedor1.setTipoFornecedor(TipoFornecedor.PESSOA_JURIDICA);
        fornecedor1.setTelefone("1199999999");
        esperado.add(fornecedor1);
        
        Fornecedor fornecedor2 = new Fornecedor();
        fornecedor2.setIdFornecedor(2);
        fornecedor2.setNome("Fornecedor B");
        fornecedor2.setDocumento("98765432109876");
        fornecedor2.setTipoFornecedor(TipoFornecedor.PESSOA_FISICA);
        fornecedor2.setTelefone("1188888888");
        esperado.add(fornecedor2);
        
        Mockito.when(fornecedorRepository.findAll()).thenReturn(esperado);

        List<Fornecedor> recebido = fornecedorService.listar();
        Assertions.assertArrayEquals(esperado.toArray(), recebido.toArray());
    }

    @Test
    void buscarPorIdQuandoAcionadoComIdValidoDeveRetornarFornecedorTest() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setIdFornecedor(1);
        fornecedor.setNome("Fornecedor Teste");
        fornecedor.setDocumento("12345678901234");
        fornecedor.setTipoFornecedor(TipoFornecedor.PESSOA_JURIDICA);
        fornecedor.setTelefone("1199999999");

        Mockito.when(fornecedorRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(fornecedor));

        Fornecedor resultado = fornecedorService.buscarPorId(1);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(fornecedor.getNome(), resultado.getNome());
    }

    @Test
    void deveRetornarListaComTresObjetosTeste() {
        List<Fornecedor> esperado = new ArrayList<>();
        
        Fornecedor fornecedor1 = new Fornecedor();
        fornecedor1.setIdFornecedor(1);
        fornecedor1.setNome("Fornecedor X");
        fornecedor1.setDocumento("11111111111111");
        fornecedor1.setTipoFornecedor(TipoFornecedor.PESSOA_JURIDICA);
        fornecedor1.setTelefone("1111111111");
        esperado.add(fornecedor1);
        
        Fornecedor fornecedor2 = new Fornecedor();
        fornecedor2.setIdFornecedor(2);
        fornecedor2.setNome("Fornecedor Y");
        fornecedor2.setDocumento("22222222222222");
        fornecedor2.setTipoFornecedor(TipoFornecedor.PESSOA_FISICA);
        fornecedor2.setTelefone("2222222222");
        esperado.add(fornecedor2);
        
        Fornecedor fornecedor3 = new Fornecedor();
        fornecedor3.setIdFornecedor(3);
        fornecedor3.setNome("Fornecedor Z");
        fornecedor3.setDocumento("33333333333333");
        fornecedor3.setTipoFornecedor(TipoFornecedor.PESSOA_JURIDICA);
        fornecedor3.setTelefone("3333333333");
        esperado.add(fornecedor3);
        
        Mockito.when(fornecedorRepository.findAll()).thenReturn(esperado);

        List<Fornecedor> recebido = fornecedorService.listar();
        Assertions.assertEquals(3, recebido.size());
    }

    @Test
    void buscarPorIdDeveRetornarFornecedorComApelidoTest() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setIdFornecedor(5);
        fornecedor.setNome("Fornecedor com Apelido");
        fornecedor.setDocumento("55555555555555");
        fornecedor.setTipoFornecedor(TipoFornecedor.PESSOA_JURIDICA);
        fornecedor.setTelefone("5555555555");
        fornecedor.setApelido("Apelido Teste");

        Mockito.when(fornecedorRepository.findById(5)).thenReturn(Optional.of(fornecedor));

        Fornecedor resultado = fornecedorService.buscarPorId(5);
        Assertions.assertEquals("Apelido Teste", resultado.getApelido());
    }

    @Test
    void deletarQuandoAcionadoComIdValidoDeveVerificarExistenciaTest() {
        Mockito.when(fornecedorRepository.existsById(10)).thenReturn(true);

        fornecedorService.deletar(10);

        Mockito.verify(fornecedorRepository, Mockito.times(1)).deleteById(10);
    }
}
