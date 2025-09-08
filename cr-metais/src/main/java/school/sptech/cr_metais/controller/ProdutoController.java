package school.sptech.cr_metais.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.Produto;
import school.sptech.cr_metais.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        Produto produtoSalvo = produtoRepository.save(produto);
        return ResponseEntity.status(201).body(produtoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        if (produtoOpt.isPresent()){
            Produto produto = produtoOpt.get();
            return ResponseEntity.status(200).body(produto);
        }return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar
            (@PathVariable Integer id, @RequestBody Produto produto){
        produto.setId(id);
        Produto produtoSalvo = produtoRepository.save(produto);
        if (produtoRepository.existsById(id)){
            return ResponseEntity.status(200).body(produtoSalvo);
        }
        return ResponseEntity.status(404).build();
    }


}
