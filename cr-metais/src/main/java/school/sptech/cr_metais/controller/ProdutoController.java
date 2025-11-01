package school.sptech.cr_metais.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.repository.ProdutoRepository;
import school.sptech.cr_metais.service.ProdutoService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.RecursiveTask;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos")
public class ProdutoController {

private final ProdutoService pService;

    public ProdutoController(ProdutoService pService) {
        this.pService = pService;
    }

    // Cadastrar produtos
    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        Produto produtoSalvo = pService.cadastrar(produto);
        return ResponseEntity.status(201).body(produtoSalvo);
    }

    // Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listar(){

        List<Produto> all = pService.listar();

        if (all.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(all);
    }

    // Deletar todos os produtos
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        pService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id){

        Produto produtoEncontrado = pService.buscarPorId(id);
        return ResponseEntity.status(200).body(produtoEncontrado);
    }

    // Atualizar Produto
    @PutMapping("{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Integer id, @RequestBody Produto produto){
        Produto produtoAtualizado = pService.atualizar(id, produto);
        return ResponseEntity.status(200).body(produtoAtualizado);
    }

    // Listar Produto por maior preço
    @GetMapping("/preco")
    public ResponseEntity<List<Produto>> listarPorPrecoMaior(){

        List<Produto> all = pService.listarPorPrecoMaior();

        if (all.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(all);
    }

}
