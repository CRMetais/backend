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
import school.sptech.cr_metais.dto.ItemPedidoVenda.ItemPedidoVendaCadastroDto;
import school.sptech.cr_metais.dto.ItemPedidoVenda.ItemPedidoVendaResponseDto;
import school.sptech.cr_metais.entity.ItemPedidoVenda;
import school.sptech.cr_metais.service.ItemPedidoVendaService;

import java.util.List;

@RestController
@RequestMapping("/itens-pedido-venda")
@Tag(name = "Item Pedido Venda")
@SecurityRequirement(name = "Bearer")
public class ItemPedidoVendaController {

    private final ItemPedidoVendaService service;

    public ItemPedidoVendaController(ItemPedidoVendaService service) {
        this.service = service;
    }

    @Operation(
            summary = "Cadastrar um novo item de venda",
            description = "Cria um item com base nas informações fornecidas no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedidoVenda.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })

    @PostMapping
    public ResponseEntity<ItemPedidoVendaResponseDto> cadastrar(@RequestBody @Valid ItemPedidoVendaCadastroDto dto) {

        ItemPedidoVendaResponseDto resposta = service.cadastrar(dto);

        return ResponseEntity.status(201).body(resposta);
    }


    @Operation(
            summary = "Listar todos os itens de venda",
            description = "Retorna uma lista com todos os itens cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de itens retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedidoVenda.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum item encontrado", content = @Content)
    })

    @GetMapping
    public ResponseEntity<List<ItemPedidoVendaResponseDto>> listar() {

        List<ItemPedidoVendaResponseDto> all = service.listar();

        if (all.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok(all);
    }


    @Operation(
            summary = "Deletar item por ID",
            description = "Remove um item do sistema com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content)
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }


    @Operation(
            summary = "Buscar item por ID",
            description = "Busca um item específico através do seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedidoVenda.class))),
            @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoVendaResponseDto> buscarPorId(@PathVariable Integer id) {

        ItemPedidoVendaResponseDto dto = service.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }


    @Operation(
            summary = "Atualizar item por ID",
            description = "Atualiza os dados de um item existente com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedidoVenda.class))),
            @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content)
    })

    @PutMapping("{id}")
    public ResponseEntity<ItemPedidoVendaResponseDto> atualizar(
            @PathVariable Integer id,
            @RequestBody ItemPedidoVendaCadastroDto dto) {

        ItemPedidoVendaResponseDto atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

}


