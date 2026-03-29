package school.sptech.cr_metais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Historico.HistoricoCompraDto;
import school.sptech.cr_metais.dto.Historico.HistoricoVendaDto;
import school.sptech.cr_metais.repository.HistoricoRepository;

import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository repository;

    public List<?> listarPorTipo(String tipo) {

        if (tipo.equalsIgnoreCase("COMPRA")) {

            List<HistoricoCompraDto> compras = repository.listarCompras()
                    .stream()
                    .map(objects -> new HistoricoCompraDto(
                            ((Number) objects[0]).intValue(),
                            ((java.sql.Date) objects[1]).toLocalDate(),
                            (String) objects[2],
                            (String) objects[3],
                            ((Number) objects[4]).doubleValue(),
                            ((Number) objects[5]).doubleValue(),
                            ((Number) objects[6]).doubleValue(),
                            objects[7] != null ? ((Number) objects[7]).doubleValue() : null,
                            (String) objects[8]
                    ))
                    .collect(java.util.stream.Collectors.toList());

            compras.sort((c1, c2) -> c2.getData().compareTo(c1.getData()));
            return compras;
        }

        if (tipo.equalsIgnoreCase("VENDA")) {

            List<HistoricoVendaDto> vendas = repository.listarVendas()
                    .stream()
                    .map(obj -> new HistoricoVendaDto(
                            ((Number) obj[0]).intValue(),
                            ((java.sql.Date) obj[1]).toLocalDate(),
                            (String) obj[2],
                            (String) obj[3],
                            ((Number) obj[4]).doubleValue(),
                            ((Number) obj[5]).doubleValue(),
                            ((Number) obj[6]).doubleValue(),
                            obj[7] != null ? ((Number) obj[7]).doubleValue() : null,
                            (String) obj[8]
                    ))
                    .collect(java.util.stream.Collectors.toList());

            vendas.sort((v1, v2) -> v2.getData().compareTo(v1.getData()));
            return vendas;
        }

        throw new IllegalArgumentException("Tipo inválido");
    }
}
