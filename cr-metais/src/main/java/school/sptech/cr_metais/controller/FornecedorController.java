package school.sptech.cr_metais.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.repository.ProdutoRepository;
import school.sptech.cr_metais.service.FornecedorService;
import school.sptech.cr_metais.service.ProdutoService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.RecursiveTask;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    private final FornecedorService fService;

    public FornecedorController(FornecedorService fService) {
        this.fService = fService;
    }


    @PostMapping
    public ResponseEntity<Fornecedor> cadastrarProduto(@Valid @RequestBody Fornecedor fornecedor) {
        return ResponseEntity.status(201).body(fService.cadastrar(fornecedor));
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> listar(){

        List<Fornecedor> all = fService.listar();

        if (all.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(all);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        fService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(fService.buscarPorId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Fornecedor> atualizar(@PathVariable Integer id, @RequestBody Fornecedor fornecedor){
        return ResponseEntity.status(200).body(fService.atualizar(id, fornecedor));
    }
}