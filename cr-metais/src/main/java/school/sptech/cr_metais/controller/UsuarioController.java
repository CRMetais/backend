package school.sptech.cr_metais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.Usuario.UsuarioCriacaoDto;
import school.sptech.cr_metais.dto.Usuario.UsuarioListarDto;
import school.sptech.cr_metais.dto.Usuario.UsuarioLoginDto;
import school.sptech.cr_metais.dto.Usuario.UsuarioTokenDto;
import school.sptech.cr_metais.entity.Usuario;
import school.sptech.cr_metais.mappers.UsuarioMapper;
import school.sptech.cr_metais.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários")
public class UsuarioController {

    @Autowired
    private final UsuarioService uService;

    public UsuarioController(UsuarioService uService) {
        this.uService = uService;
    }

    @Operation(summary = "Cadastrar usuário", description = "Cria um novo usuário no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioListarDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"erro\": \"Email ou senha inválidos\"}")))
    })
    @SecurityRequirement(name = "Bearer")
    @PostMapping() // Usando uma rota específica para evitar ambiguidade
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {
        // 1. Converte o DTO (dados de entrada) para a entidade Usuario
        Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);

        // 2. Chama o serviço para aplicar a lógica de negócio (ex: criptografar senha) e salvar
        this.uService.criar(novoUsuario);

        // 3. Retorna uma resposta de sucesso (201 Created)
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioListarDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"erro\": \"Usuário não encontrado\"}")))
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
       Usuario usuarioEncontrado = uService.buscarPorId(id);
       return ResponseEntity.status(200).body(usuarioEncontrado);
    }

    @Operation(summary = "Deletar usuário", description = "Remove um usuário pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"erro\": \"Usuário não encontrado\"}")))
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {

        uService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioListarDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"erro\": \"Usuário não encontrado\"}")))
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody Usuario usuario){

        Usuario usuarioAtualizado = uService.atualizar(id, usuario);
        return ResponseEntity.status(200).body(usuarioAtualizado);
    }

    @Operation(summary = "Login de usuário", description = "Autentica o usuário e retorna o token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioTokenDto.class))),
            @ApiResponse(responseCode = "401", description = "Credenciais incorretas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"erro\": \"Credenciais inválidas\"}")))
    })

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto){
        UsuarioTokenDto usuarioTokenDto = this.uService.autenticar(UsuarioMapper.of(usuarioLoginDto));
        return ResponseEntity.status(200).body(usuarioTokenDto);
    }

    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioListarDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum usuário encontrado")
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<UsuarioListarDto>> listarTodos(){

        List<UsuarioListarDto> usuariosEncontrados = this.uService.listarTodos();

        if (usuariosEncontrados.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuariosEncontrados);
    }
}
