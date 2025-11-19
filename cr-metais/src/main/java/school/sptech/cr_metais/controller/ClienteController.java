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

import school.sptech.cr_metais.entity.Cliente;
import school.sptech.cr_metais.entity.TabelaPreco;

import school.sptech.cr_metais.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes")
@SecurityRequirement(name = "Bearer")
public class ClienteController {

    private final ClienteService cService;

    public ClienteController(ClienteService cService) {
        this.cService = cService;
    }

    @Operation(
            summary = "Cadastrar um novo cliente",
            description = "Cadastrar um novo cliente com base nas informações fornecidas no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClienteResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid ClienteCadastroDTO dto) {

        Cliente resposta = cService.cadastrar(dto);

        return ResponseEntity.status(201).body(resposta);
    }


    @Operation(
            summary = "Listar todos os clientes",
            description = "Retorna uma lista com todos os clientes cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClienteResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum cliente encontrado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {

        List<ClienteResponseDTO> all = cService.listar();

        if (all.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok(all);
    }



    @Operation(
            summary = "Deletar cliente por ID",
            description = "Remove um cliente do sistema com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        cService.deletar(id);
        return ResponseEntity.status(204).build();
    }


    @Operation(
            summary = "Buscar cliente por ID",
            description = "Busca um cliente específico através do seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClienteResponseDTO.class)
                    )),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Integer id) {
        ClienteResponseDTO dto = cService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }


    @Operation(
            summary = "Atualizar tabela por ID",
            description = "Atualiza os dados de uma tabela existente com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tabela atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TabelaPreco.class))),
            @ApiResponse(responseCode = "404", description = "Tabela não encontrada", content = @Content)
    })

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(
            @PathVariable Integer id,
            @RequestBody ClienteCadastroDTO dto) {

        ClienteResponseDTO atualizada = cService.atualizar(id,  dto);
        return ResponseEntity.ok(atualizada);
    }


}