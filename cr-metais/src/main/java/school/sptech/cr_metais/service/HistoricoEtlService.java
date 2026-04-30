package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Historico.TransacaoHistoricoDto;
import school.sptech.cr_metais.repository.HistoricoRepository;
import school.sptech.cr_metais.repository.TransacaoHistoricoProjection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoricoEtlService {

    private final HistoricoRepository historicoRepository;

    public HistoricoEtlService(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    public List<TransacaoHistoricoDto> buscarTudo(LocalDate dataInicio, LocalDate dataFim, String tipo) {
        List<TransacaoHistoricoProjection> raw = new ArrayList<>();

        if (tipo.equalsIgnoreCase("COMPRA") || tipo.equalsIgnoreCase("TODOS")) {
            raw.addAll(historicoRepository.buscarCompras(dataInicio, dataFim));
        }
        if (tipo.equalsIgnoreCase("VENDA") || tipo.equalsIgnoreCase("TODOS")) {
            raw.addAll(historicoRepository.buscarVendas(dataInicio, dataFim));
        }

        return raw.stream()
                .map(p -> new TransacaoHistoricoDto(
                        p.getId(),
                        p.getData().toLocalDate(),
                        p.getParceiro(),
                        p.getProduto(),
                        p.getPeso(),
                        p.getPreco(),
                        p.getTotal(),
                        p.getRendimento(),
                        p.getTipo()
                ))
                .sorted((a, b) -> b.getData().compareTo(a.getData()))
                .toList();
    }
}