package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Dashboard.*;
import school.sptech.cr_metais.repository.DashboardRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public DashboardResponseDto getDashboard() {
        DashboardResponseDto response = new DashboardResponseDto();
        response.setInfoFornecedor(getInfoFornecedor());
        response.setInfoProduto(getInfoProduto());
        response.setAnaliseVariacao(getAnaliseVariacao());
        return response;
    }

    // ── 1. Info fornecedor ────────────────────────────────────────────────────
    private List<InfoFornecedorDto> getInfoFornecedor() {
        List<Object[]> rows = dashboardRepository.infoFornecedor();
        List<InfoFornecedorDto> result = new ArrayList<>();
        for (Object[] row : rows) {
            result.add(new InfoFornecedorDto(
                    (String)  row[0],                        // nome_fornecedor
                    ((Number) row[1]).intValue(),             // ano
                    ((Number) row[2]).intValue(),             // mes
                    row[3] != null ? ((Number) row[3]).doubleValue() : 0.0,  // peso_total
                    row[4] != null ? ((Number) row[4]).doubleValue() : 0.0   // rendimento_total
            ));
        }
        return result;
    }

    // ── 2. Info produto ───────────────────────────────────────────────────────
    private List<InfoProdutoDto> getInfoProduto() {
        List<Object[]> rows = dashboardRepository.infoProduto();
        List<InfoProdutoDto> result = new ArrayList<>();
        for (Object[] row : rows) {
            result.add(new InfoProdutoDto(
                    (String)  row[0],                        // produto
                    ((Number) row[1]).intValue(),             // ano
                    ((Number) row[2]).intValue(),             // mes
                    row[3] != null ? ((Number) row[3]).doubleValue() : 0.0,  // rendimento_total
                    row[4] != null ? ((Number) row[4]).doubleValue() : 0.0   // peso_total
            ));
        }
        return result;
    }

    // ── 3. Análise variação cobre ─────────────────────────────────────────────
    private List<AnaliseVariacaoDto> getAnaliseVariacao() {
        List<Object[]> rows = dashboardRepository.analiseVariacaoCobre();
        List<AnaliseVariacaoDto> result = new ArrayList<>();
        for (Object[] row : rows) {
            result.add(new AnaliseVariacaoDto(
                    (String) row[0],                                                      // mes
                    row[1] != null ? ((Number) row[1]).doubleValue() : null,             // media_preco
                    row[2] != null ? ((Number) row[2]).doubleValue() : null              // variacao_percentual
            ));
        }
        return result;
    }
}