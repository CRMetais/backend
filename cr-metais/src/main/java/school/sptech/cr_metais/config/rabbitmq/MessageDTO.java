package school.sptech.cr_metais.config.rabbitmq;

import java.io.Serializable;

public record MessageDTO(
        String email,
        String conteudo,
        String nomeArquivo
) implements Serializable {}