package school.sptech.cr_metais.service.factory;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.entity.TipoFornecedor;
import school.sptech.cr_metais.service.strategy.ValidacaoCadastroFornecedorStrategy;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class ValidacaoFornecedorStrategyFactory {

    private final Map<TipoFornecedor, ValidacaoCadastroFornecedorStrategy> strategyMap;

    public ValidacaoFornecedorStrategyFactory(List<ValidacaoCadastroFornecedorStrategy> strategies) {
        strategyMap = new EnumMap<>(TipoFornecedor.class);
        for (ValidacaoCadastroFornecedorStrategy strategy : strategies) {
            strategyMap.put(strategy.getTipo(), strategy);
        }
    }

    public ValidacaoCadastroFornecedorStrategy getStrategy(TipoFornecedor tipo) {
        ValidacaoCadastroFornecedorStrategy strategy = strategyMap.get(tipo);
        if (strategy == null) {
            throw new IllegalArgumentException("Nenhuma estratégia de validação encontrada para o tipo: " + tipo);
        }
        return strategy;
    }
}