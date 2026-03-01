package school.sptech.cr_metais.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.cr_metais.dto.Usuario.UsuarioLoginDto;
import school.sptech.cr_metais.dto.Usuario.UsuarioTokenDto;
import school.sptech.cr_metais.service.UsuarioService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)
class UsuarioControllerLoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    @DisplayName("POST /usuarios/login deve retornar 200 e token com credenciais válidas")
    void deveRetornar200NoLoginValido() throws Exception {
        UsuarioLoginDto loginDto = new UsuarioLoginDto();
        loginDto.setEmail("usuario@empresa.com");
        loginDto.setSenha("123456");

        UsuarioTokenDto tokenDto = new UsuarioTokenDto();
        tokenDto.setUserId(1);
        tokenDto.setNome("Usuario Teste");
        tokenDto.setEmail("usuario@empresa.com");
        tokenDto.setToken("jwt.token");

        when(usuarioService.autenticar(any())).thenReturn(tokenDto);

        mockMvc.perform(post("/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt.token"));
    }

    @Test
    @DisplayName("POST /usuarios/login deve retornar 401 com credenciais inválidas")
    void deveRetornar401NoLoginInvalido() throws Exception {
        UsuarioLoginDto loginDto = new UsuarioLoginDto();
        loginDto.setEmail("usuario@empresa.com");
        loginDto.setSenha("senhaErrada");

        when(usuarioService.autenticar(any()))
                .thenThrow(new ResponseStatusException(UNAUTHORIZED, "Credenciais inválidas"));

        mockMvc.perform(post("/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isUnauthorized());
    }
}
