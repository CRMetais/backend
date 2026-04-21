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
import school.sptech.cr_metais.dto.TabelaPreco.*;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.service.TabelaPrecoService;

import java.util.List;

@RestController
@RequestMapping("/tabelas-precos")
@Tag(name = "Tabelas Preço")
@SecurityRequirement(name = "Bearer")
public class TabelaPrecoController {

    @Autowired
    private final TabelaPrecoService tService;

    public TabelaPrecoController(TabelaPrecoService tService) {
        this.tService = tService;
    }

    @Operation(
            summary = "Cadastrar uma nova tabela de preço",
            description = "Cria uma tabela com base nas informações fornecidas no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tabela cadastrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TabelaPreco.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })

    @PostMapping
    public ResponseEntity<TabelaPrecoResponseDTO> cadastrar(@RequestBody @Valid TabelaPrecoCadastroDTO dto) {

        TabelaPrecoResponseDTO resposta = tService.cadastrar(dto);

        return ResponseEntity.status(201).body(resposta);
    }


    @Operation(
            summary = "Listar todos as tabelas",
            description = "Retorna uma lista com todos as tabelas cadastradas no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tabelas retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TabelaPreco.class))),
            @ApiResponse(responseCode = "204", description = "Nenhuma tabela encontrada", content = @Content)
    })
    // Listar todos as tabelas
    @GetMapping
    public ResponseEntity<List<TabelaPrecoResponseDTO>> listar() {

        List<TabelaPrecoResponseDTO> all = tService.listar();

        if (all.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok(all);
    }

        @Operation(
                        summary = "Listar fornecedores com tabela de preço",
                        description = "Retorna fornecedores com dados básicos e o nome da tabela de preço associada."
        )
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
                        @ApiResponse(responseCode = "204", description = "Nenhum fornecedor encontrado", content = @Content)
        })
        @GetMapping("/fornecedores")
        public ResponseEntity<List<FornecedorTabelaPrecoDto>> listarFornecedoresComTabelaPreco() {
                List<FornecedorTabelaPrecoDto> fornecedores = tService.listarFornecedoresComTabelaPreco();

                if (fornecedores.isEmpty()) {
                        return ResponseEntity.status(204).build();
                }

                return ResponseEntity.ok(fornecedores);
        }


    @Operation(
            summary = "Deletar tabela por ID",
            description = "Remove uma tabela do sistema com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tabela deletada com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Tabela não encontrada", content = @Content)
    })
    // Deletar tabela por ID
        @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        tService.deletar(id);
        return ResponseEntity.status(204).build();
    }


    @Operation(
            summary = "Buscar tabela por ID",
            description = "Busca uma tabela específica através do seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tabela encontrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TabelaPreco.class))),
            @ApiResponse(responseCode = "404", description = "Tabela não encontrada", content = @Content)
    })
    // Buscar tabela por id
        @GetMapping("/{id:\\d+}")
    public ResponseEntity<TabelaPrecoResponseDTO> buscarPorId(@PathVariable Integer id) {

        TabelaPrecoResponseDTO dto = tService.buscarPorId(id);
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
    // Atualizar tabela
    @PutMapping("{id}")
    public ResponseEntity<TabelaPrecoResponseDTO> atualizar(
            @PathVariable Integer id,
            @RequestBody TabelaPrecoCadastroDTO dto) {

        TabelaPrecoResponseDTO atualizada = tService.atualizar(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    @Operation(
            summary = "Buscar preços por nome da tabela",
            description = "Retorna todos os produtos com seus preços e datas para uma tabela específica."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preços encontrados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum preço encontrado", content = @Content)
    })
    @GetMapping("/precos")
    public ResponseEntity<List<TabelaPrecoDto>> buscarPrecosPorNome(
            @RequestParam String nome) {

        List<TabelaPrecoDto> precos = tService.buscarPrecosporNomeTabela(nome);

        if (precos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok(precos);
    }

    @PostMapping("/venda")
    public ResponseEntity<TabelaPrecoResponseDTO> cadastrarTabelaVenda(
            @RequestBody @Valid CadastrarTabelaVendaDto dto) {

        TabelaPrecoResponseDTO resposta = tService.cadastrarTabelaVenda(dto.getNomeTabela());
        return ResponseEntity.status(201).body(resposta);
    }

    @GetMapping("/venda")
    public ResponseEntity<List<TabelaPrecoResponseDTO>> listarVenda() {
        List<TabelaPrecoResponseDTO> all = tService.listarVenda();
        if (all.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.ok(all);
    }

}


