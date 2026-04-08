package school.sptech.cr_metais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Resumo.ProdutoResumoDto;
import school.sptech.cr_metais.dto.Resumo.ResumoDto;
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

                    Double peso = r[1] == null ? 0.0 : ((Number) r[1]).doubleValue();

                    Double valor = r[2] == null ? 0.0 : ((Number) r[2]).doubleValue();

                    String destino = r[3] == null ? "-" : (String) r[3];

                    return new ProdutoResumoDto(
                            (String) r[0],
                            peso,
                            valor,
                            destino
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
        resumo.setTotalAplicado(totalAplicado);
        resumo.setPesoTotal(pesoTotal);
        resumo.setNotasHoje(notasHoje);
        resumo.setPesoHoje(pesoHoje);

        return resumo;
    }
}
