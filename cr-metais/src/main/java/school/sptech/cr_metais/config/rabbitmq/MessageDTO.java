package school.sptech.cr_metais.config.rabbitmq;

import java.io.Serializable;

public record MessageDTO(
        String email,
        String html  // ✅ substituído "conteudo" e "nomeArquivo" pelo campo real
) implements Serializable {}