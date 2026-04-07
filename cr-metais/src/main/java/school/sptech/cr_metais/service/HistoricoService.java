package school.sptech.cr_metais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Historico.HistoricoCompraDto;
import school.sptech.cr_metais.dto.Historico.HistoricoVendaDto;
import school.sptech.cr_metais.repository.HistoricoRepository;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository repository;

    public Page<?> listarPorTipo(String tipo, int pagina, int tamanho) {

        Pageable pageable = PageRequest.of(pagina, tamanho);

        if (tipo.equalsIgnoreCase("COMPRA")) {

            return repository.listarCompras(pageable)
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
                    ));
        }

        if (tipo.equalsIgnoreCase("VENDA")) {

            return repository.listarVendas(pageable)
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
                    ));
        }

        throw new IllegalArgumentException("Tipo inválido");
    }
}