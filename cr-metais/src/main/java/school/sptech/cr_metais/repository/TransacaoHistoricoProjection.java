package school.sptech.cr_metais.repository;

import java.sql.Date;

public interface TransacaoHistoricoProjection {
    Integer getId();
    Date getData();
    String getParceiro();
    String getProduto();
    Double getPeso();
    Double getPreco();
    Double getTotal();
    Double getRendimento();
    String getTipo();
}