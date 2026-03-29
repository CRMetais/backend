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
            List<HistoricoCompraDto> compras = repository.listarCompras();

            compras.sort((c1, c2) -> c2.getData().compareTo(c1.getData()));
            return compras;
        }

        if (tipo.equalsIgnoreCase("VENDA")) {
            List<HistoricoVendaDto> vendas = repository.listarVendas();

            vendas.sort((v1, v2) -> v2.getData().compareTo(v1.getData()));
            return vendas;
        }

        // opcional: se vier inválido
        throw new IllegalArgumentException("Tipo inválido. Use 'entrada' ou 'saida'");
    }
}
