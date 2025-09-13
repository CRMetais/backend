package school.sptech.cr_metais.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.entity.Usuario;
import school.sptech.cr_metais.repository.UsuarioRepository;
import school.sptech.cr_metais.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService uService;

    public UsuarioController(UsuarioService uService) {
        this.uService = uService;
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = uService.cadastrar(usuario);
        return ResponseEntity.status(201).body(usuarioSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = uService.listar();

        if(usuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(201).body(usuarios);
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
}
