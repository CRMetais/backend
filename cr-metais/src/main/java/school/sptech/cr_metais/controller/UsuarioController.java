package school.sptech.cr_metais.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.Usuario;
import school.sptech.cr_metais.Usuario;
import school.sptech.cr_metais.repository.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return ResponseEntity.status(201).body(usuarioSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.of(usuarioRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar
            (@PathVariable Integer id, @RequestBody Usuario usuario){
        usuario.setId(id);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        if (usuarioRepository.existsById(id)){
            return ResponseEntity.status(200).body(usuarioSalvo);
        }
        return ResponseEntity.status(404).build();
    }
}
