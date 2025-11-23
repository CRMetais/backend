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
import school.sptech.cr_metais.dto.Cliente.ClienteCadastroDTO;
import school.sptech.cr_metais.dto.Cliente.ClienteResponseDTO;
import school.sptech.cr_metais.dto.Venda.VendaCadastroDTO;
import school.sptech.cr_metais.dto.Venda.VendaResponseDTO;
import school.sptech.cr_metais.entity.Cliente;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.entity.Venda;
import school.sptech.cr_metais.service.ClienteService;
import school.sptech.cr_metais.service.VendaService;

import java.util.List;

@RestController
@RequestMapping("/vendas")
@Tag(name = "Vendas")
@SecurityRequirement(name = "Bearer")
public class VendaController {

    private final VendaService vService;

    public VendaController(VendaService vService) {
        this.vService = vService;
    }

    @Operation(
            summary = "Fazer uma nova venda",
            description = "Cadastrar uma nova venda com base nas informações fornecidas no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venda cadastrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VendaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Venda> cadastrar(@RequestBody @Valid VendaCadastroDTO dto) {

        Venda resposta = vService.cadastrar(dto);

        return ResponseEntity.status(201).body(resposta);
    }


    @Operation(
            summary = "Listar todas as vendas",
            description = "Retorna uma lista com todas as vendas cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de vendas retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = VendaResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Nenhuma venda encontrada", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<VendaResponseDTO>> listar() {

        List<VendaResponseDTO> all = vService.listar();

        if (all.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok(all);
    }

    @Operation(
            summary = "Deletar venda por ID",
            description = "Remove uma venda do sistema com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Venda deletada com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada", content = @Content)
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        vService.deletar(id);
        return ResponseEntity.status(204).build();
    }


    @Operation(
            summary = "Buscar venda por ID",
            description = "Busca uma venda específica através do seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda encontrada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Venda.class)
                    )),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<VendaResponseDTO> buscarPorId(@PathVariable Integer id) {
        VendaResponseDTO dto = vService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }


}
