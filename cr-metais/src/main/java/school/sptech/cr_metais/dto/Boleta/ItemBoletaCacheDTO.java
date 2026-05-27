package school.sptech.cr_metais.dto.Boleta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // <- ADICIONADO: Cria Getters, Setters, toString, etc.
@NoArgsConstructor // <- ADICIONADO: Essencial para o Jackson converter o JSON do Redis
@AllArgsConstructor // <- ADICIONADO: Construtor cheio
public class ItemBoletaCacheDTO {
    private Long idLinha; // Date.now() do React
    private String produtoId;
    private Double peso;
    private Integer bags;
    private Double valorUnitario;
    private Double total;
}