package school.sptech.cr_metais.repository;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.entity.TransacaoEtl;
import school.sptech.cr_metais.service.EtlDataPort;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class EtlRepositoryAdapter implements EtlDataPort {

    private final EtlRepository etlRepository;

    public EtlRepositoryAdapter(EtlRepository etlRepository) {
        this.etlRepository = etlRepository;
    }

    @Override
    public List<TransacaoEtl> extrairTudo() {
        List<TransacaoEtl> resultado = new ArrayList<>();

        // Processar Compras
        etlRepository.buscarComprasRaw().forEach(p -> {
            LocalDateTime dataHora = p.getData().toLocalDate().atStartOfDay();
            resultado.add(new TransacaoEtl(p.getId(), dataHora, p.getParceiro(), p.getProduto(),
                    p.getPeso(), p.getPreco(), p.getTotal(), "COMPRA", p.getRendimento()));
        });

        // Processar Vendas
        etlRepository.buscarVendasRaw().forEach(p -> {
            LocalDateTime dataHora = p.getData().toLocalDate().atStartOfDay();
            resultado.add(new TransacaoEtl(p.getId(), dataHora, p.getParceiro(), p.getProduto(),
                    p.getPeso(), p.getPreco(), p.getTotal(), "VENDA", null));
        });

        resultado.sort((a, b) -> b.getData().compareTo(a.getData()));
        return resultado;
    }
}