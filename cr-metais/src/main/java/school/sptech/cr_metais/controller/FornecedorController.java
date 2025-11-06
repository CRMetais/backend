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
import school.sptech.cr_metais.dto.Fornecedor.FornecedorCadastroDto;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.service.FornecedorService;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
@Tag(name = "Fornecedores")
@SecurityRequirement(name = "Bearer")
public class FornecedorController {

    private final FornecedorService fService;

    public FornecedorController(FornecedorService fService) {
        this.fService = fService;
    }


    @Operation(
            summary = "Cadastrar um novo fornecedor",
            description = "Cria um novo fornecedor no sistema com base nas informações enviadas no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Fornecedor cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fornecedor.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou incompletos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Fornecedor> cadastrarFornecedor(@RequestBody @Valid FornecedorCadastroDto dto) {
        Fornecedor fornecedorSalvo = fService.cadastrar(dto);
        return ResponseEntity.status(201).body(fornecedorSalvo);
    }


    @Operation(
            summary = "Listar todos os fornecedores",
            description = "Retorna uma lista contendo todos os fornecedores cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de fornecedores retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fornecedor.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum fornecedor encontrado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Fornecedor>> listar(){
        List<Fornecedor> all = fService.listar();
        if (all.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(all);
    }


    @Operation(
            summary = "Buscar fornecedor por ID",
            description = "Busca e retorna um fornecedor específico com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fornecedor encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fornecedor.class))),
            @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Integer id){
        Fornecedor fornecedorEncontrado = fService.buscarPorId(id);
        return ResponseEntity.status(200).body(fornecedorEncontrado);
    }


    @Operation(
            summary = "Deletar fornecedor por ID",
            description = "Remove permanentemente um fornecedor do sistema com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Fornecedor deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        fService.deletar(id);
        return ResponseEntity.status(204).build();
    }


    @Operation(
            summary = "Listar fornecedores em ordem invertida",
            description = "Retorna todos os fornecedores em ordem inversa de cadastro, utilizando abordagem recursiva."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de fornecedores invertida retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fornecedor.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum fornecedor encontrado", content = @Content)
    })
    @GetMapping("/invertido")
    public ResponseEntity<List<Fornecedor>> listarInversoRecursivo(){

        List<Fornecedor> listaInvertida = fService.listarInversoRecursivo();

        if (listaInvertida.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaInvertida);
    }

}