package school.sptech.cr_metais.dto.Boleta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoletaCacheDTO {
    private Integer id;
    private List<ItemBoletaCacheDTO> itensBoleta; // Agora vai funcionar porque a classe interna tem construtor!
    private String clienteSelecionadoId;
    private String classeNota;
    private String tipoNota;
    private Boolean pagamentoConfirmado;
}