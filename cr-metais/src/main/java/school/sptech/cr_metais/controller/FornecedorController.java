package school.sptech.cr_metais.controller;

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

    @PostMapping
    public ResponseEntity<Fornecedor> cadastrarFornecedor(@RequestBody @Valid FornecedorCadastroDto dto) {
        Fornecedor fornecedorSalvo = fService.cadastrar(dto);
        return ResponseEntity.status(201).body(fornecedorSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> listar(){
        List<Fornecedor> all = fService.listar();
        if (all.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Integer id){
        Fornecedor fornecedorEncontrado = fService.buscarPorId(id);
        return ResponseEntity.status(200).body(fornecedorEncontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        fService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/invertido")
    public ResponseEntity<List<Fornecedor>> listarInversoRecursivo(){

        List<Fornecedor> listaInvertida = fService.listarInversoRecursivo();

        if (listaInvertida.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaInvertida);
    }

}