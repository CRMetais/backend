package school.sptech.cr_metais.service;

import school.sptech.cr_metais.entity.TransacaoEtl;

import java.util.List;

public interface EtlDataPort {

    List<TransacaoEtl> extrairTudo();

}
