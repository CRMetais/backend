package school.sptech.cr_metais.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.EnderecoCadastroDto.EnderecoCadastroDto;
import school.sptech.cr_metais.dto.EnderecoCadastroDto.EnderecoGetDto;
import school.sptech.cr_metais.entity.Endereco;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.service.EnderecoService;

@RestController
//@RequestMapping("/fornecedores")
@RequestMapping("/enderecos")
@Tag(name = "Endereco")
@SecurityRequirement(name = "Bearer")
public class EnderecoController {

        private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Endereco> cadastrarEndereco(@RequestBody @Valid EnderecoCadastroDto dto) {
        Endereco endereco = service.cadastrar(dto);
        return ResponseEntity.ok(endereco);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoGetDto> buscarPorId(@PathVariable Integer id){
        EnderecoGetDto endereco = service.buscarPorId(id);
        if (endereco == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(endereco);
    }
}
