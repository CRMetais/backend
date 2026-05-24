package school.sptech.cr_metais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Resumo.ClienteResumoDto;
import school.sptech.cr_metais.dto.Resumo.ProdutoResumoDto;
import school.sptech.cr_metais.dto.Resumo.ResumoDto;
import school.sptech.cr_metais.dto.Resumo.TabelaPrecoResumoDto;
import school.sptech.cr_metais.repository.ResumoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ResumoService {

    @Autowired
    private ResumoRepository repository;

    public ResumoDto buscarResumo(){

        List<Object[]> resultado = repository.buscarResumoProdutos();

        List<ProdutoResumoDto> produtos = resultado.stream()
                .map(r -> {

                    Double pesoComprado = r[1] == null ? 0.0 : ((Number) r[1]).doubleValue();

                    Double pesoVendido = r[2] == null ? 0.0 : ((Number) r[2]).doubleValue();

                    Double materialDisponivel = r[3] == null ? 0.0 : ((Number) r[3]).doubleValue();

                    return new ProdutoResumoDto(
                            (String) r[0],
                            pesoComprado,
                            pesoVendido,
                            materialDisponivel
                    );
                }).toList();

        List<String> resultadoClientes = repository.buscarClientesResumo();

        List<ClienteResumoDto> clientes = resultadoClientes.stream()
                .map(ClienteResumoDto::new)
                .toList();

        List<Object[]> resultadoTabelas = repository.buscarTabelasResumo();

        List<TabelaPrecoResumoDto> tabelasPreco = resultadoTabelas.stream()
                .map(r -> {

                    Double preco = r[2] == null
                            ? 0.0
                            : ((Number) r[2]).doubleValue();

                    return new TabelaPrecoResumoDto(
                            (String) r[0],
                            (String) r[1],
                            preco
                    );
                }).toList();

        ResumoDto resumo = new ResumoDto();
        LocalDate hoje = LocalDate.now();

        Double totalAplicado = repository.totalAplicado();
        if(totalAplicado == null){
            totalAplicado = 0.0;
        }

        Double pesoTotal = repository.pesoTotal();
        if(pesoTotal == null){
            pesoTotal = 0.0;
        }

        Double notasHoje = repository.notasHoje(hoje);
        if (notasHoje == null){
            notasHoje = 0.0;
        }

        Double pesoHoje = repository.pesoHoje(hoje);
        if(pesoHoje == null){
            pesoHoje = 0.0;
        }

        resumo.setProdutos(produtos);
        resumo.setClientes(clientes);
        resumo.setTabelasPreco(tabelasPreco);

        resumo.setTotalAplicado(totalAplicado);
        resumo.setPesoTotal(pesoTotal);
        resumo.setNotasHoje(notasHoje);
        resumo.setPesoHoje(pesoHoje);

        return resumo;
    }
}
