package school.sptech.cr_metais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.Compra.CompraCadastroDto;
import school.sptech.cr_metais.dto.Compra.CompraResponseDto;
import school.sptech.cr_metais.dto.Usuario.UsuarioListarDto;
import school.sptech.cr_metais.entity.Compra;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.entity.PagamentoCompra;
import school.sptech.cr_metais.mappers.CompraMapper;
import school.sptech.cr_metais.service.CompraService;

import java.util.List;

@RequestMapping("/compra")
@RestController
@Tag(name = "Compra")
@SecurityRequirement(name = "Bearer")
public class CompraController {

    final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @Operation(
            summary = "Cadastrar uma nova Compra",
            description = "Cria uma nova compra no sistema com base nas informações enviadas no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Compra cadastrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fornecedor.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou incompletos", content = @Content)
    })

    @PostMapping
    public ResponseEntity<Compra> cadastrarCompra(@RequestBody @Valid CompraCadastroDto dto){

        Compra compraSalva = compraService.cadastrar(dto);
        return ResponseEntity.status(201).body(compraSalva);
    }

    @Operation(summary = "Listar todas as compras", description = "Retorna uma lista de todos as compras cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de compras retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioListarDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhuma compra encontrado")
    })

    @GetMapping
    public ResponseEntity<List<CompraResponseDto>> listar() {
        List<Compra> allCompras = compraService.listar();
        if (allCompras.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<CompraResponseDto> response = CompraMapper.toResponse(allCompras);
        return ResponseEntity.status(200).body(response);
    }
}
