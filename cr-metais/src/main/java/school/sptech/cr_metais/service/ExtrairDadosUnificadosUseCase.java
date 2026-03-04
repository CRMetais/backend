package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.entity.TransacaoEtl;

import java.util.List;

@Service
public class ExtrairDadosUnificadosUseCase {
    private final EtlDataPort dataPort;

    public ExtrairDadosUnificadosUseCase(EtlDataPort dataPort) {
        this.dataPort = dataPort;
    }

    public List<TransacaoEtl> executar() {
        return dataPort.extrairTudo();
    }
}
