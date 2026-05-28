package school.sptech.cr_metais.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Usuario.RascunhoColaboradorDto;

import java.time.Duration;

@Service
public class RascunhoColaboradorService {

    private static final String PREFIX = "rascunho:colaborador:";
    private static final Duration TTL   = Duration.ofHours(2);

    private final RedisTemplate<String, Object> redisTemplate;

    public RascunhoColaboradorService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void salvar(String chave, RascunhoColaboradorDto dto) {
        redisTemplate.opsForValue().set(PREFIX + chave, dto, TTL);
    }

    public RascunhoColaboradorDto buscar(String chave) {
        Object valor = redisTemplate.opsForValue().get(PREFIX + chave);
        if (valor instanceof RascunhoColaboradorDto dto) return dto;
        return null;
    }

    public void deletar(String chave) {
        redisTemplate.delete(PREFIX + chave);
    }
}
