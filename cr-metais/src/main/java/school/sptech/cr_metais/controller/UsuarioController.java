package school.sptech.cr_metais.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.*;
import school.sptech.cr_metais.entity.Usuario;
import school.sptech.cr_metais.mappers.UsuarioMapper;
import school.sptech.cr_metais.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioService uService;

    public UsuarioController(UsuarioService uService) {
        this.uService = uService;
    }

    @PostMapping("/cadastrar") // Usando uma rota específica para evitar ambiguidade
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {

        // 1. Converte o DTO (dados de entrada) para a entidade Usuario
        Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);

        // 2. Chama o serviço para aplicar a lógica de negócio (ex: criptografar senha) e salvar
        this.uService.criar(novoUsuario);

        // 3. Retorna uma resposta de sucesso (201 Created)
        return ResponseEntity.status(201).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {

       Usuario usuarioEncontrado = uService.buscarPorId(id);
       return ResponseEntity.status(200).body(usuarioEncontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {

        uService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar
            (@PathVariable Integer id, @RequestBody Usuario usuario){

        Usuario usuarioAtualizado = uService.atualizar(id, usuario);
        return ResponseEntity.status(200).body(usuarioAtualizado);
    }


    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto){

        UsuarioTokenDto usuarioTokenDto = this.uService.autenticar(UsuarioMapper.of(usuarioLoginDto));
        return ResponseEntity.status(200).body(usuarioTokenDto);
    }

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
