package school.sptech.cr_metais.dto.Relatorio;

import java.io.Serializable;

public record RelatorioDTO(
        String email,
        String html
) implements Serializable {}