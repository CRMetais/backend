package school.sptech.cr_metais.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Boleta.BoletaCacheDTO;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoletaRascunhoService {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final String CACHE_PREFIX = "boletas::";

    public List<BoletaCacheDTO> buscarRascunho(String username) {
        String chave = CACHE_PREFIX + username;
        try {
            // Busca o objeto puro do Redis
            Object rascunho = redisTemplate.opsForValue().get(chave);

            if (rascunho instanceof List) {
                return (List<BoletaCacheDTO>) rascunho;
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler do Redis, gerando lista vazia: " + e.getMessage());
        }

        System.out.println("Cache MISS para o usuário: " + username + ". Retornando rascunho limpo.");
        return new ArrayList<>(); // Retorna uma lista editável se não achar nada
    }

    public List<BoletaCacheDTO> salvarRascunho(String username, List<BoletaCacheDTO> boletas) {
        String chave = CACHE_PREFIX + username;

        // Salva direto no Redis definindo o tempo de expiração de 2 horas
        redisTemplate.opsForValue().set(chave, boletas, Duration.ofHours(2));
        System.out.println("Cache atualizado com sucesso via RedisTemplate para: " + username);

        return boletas;
    }

    public void limparRascunho(String username) {
        String chave = CACHE_PREFIX + username;
        redisTemplate.delete(chave);
        System.out.println("Cache deletado do Redis para o usuário: " + username);
    }
}