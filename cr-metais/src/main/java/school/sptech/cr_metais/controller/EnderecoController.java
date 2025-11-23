package school.sptech.cr_metais.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.EnderecoCadastroDto.EnderecoRequestDto;
import school.sptech.cr_metais.dto.EnderecoCadastroDto.EnderecoResponseDto;
import school.sptech.cr_metais.entity.Endereco;
import school.sptech.cr_metais.mappers.EnderecoMapper;
import school.sptech.cr_metais.service.EnderecoService;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
@Tag(name = "Endereços")
@SecurityRequirement(name = "Bearer")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDto> cadastrar(@RequestBody @Valid EnderecoRequestDto dto) {

        Endereco enderecoParaCadastrar = EnderecoMapper.toEntity(dto);
        Endereco enderecoCadastrado = service.cadastrar(enderecoParaCadastrar);
        EnderecoResponseDto response = EnderecoMapper.toResponse(enderecoCadastrado);
        return ResponseEntity.status(201).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> buscarPorId(@PathVariable Integer id) {
        Endereco endereco = service.buscarPorId(id);
        EnderecoResponseDto response = EnderecoMapper.toResponse(endereco);
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid EnderecoRequestDto dto
    ) {

        Endereco entity = EnderecoMapper.toEntity(dto);
        entity.setId(id);

        Endereco enderecoAtualizado = service.atualizar(entity);
        EnderecoResponseDto response = EnderecoMapper.toResponse(enderecoAtualizado);
        return ResponseEntity.status(200).body(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping
    public ResponseEntity<List<EnderecoResponseDto>> listar() {
        List<Endereco> enderecos = service.listar();

        if (enderecos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EnderecoResponseDto> response =
                enderecos.stream()
                        .map(EnderecoMapper::toResponse)
                        .toList();

        return ResponseEntity.status(200).body(response);
    }
}

