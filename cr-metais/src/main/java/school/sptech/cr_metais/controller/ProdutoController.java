package school.sptech.cr_metais.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.repository.ProdutoRepository;
import school.sptech.cr_metais.service.ProdutoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
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


//    @GetMapping
//    public ResponseEntity<List<Produto>> listar() {
//        List<Produto> produtos = produtoRepository.findAll();
//        if (produtos.isEmpty()){
//            return ResponseEntity.status(204).build();
//        }
//        return ResponseEntity.status(200).body(produtos);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id) {
//        Optional<Produto> produtoOpt = produtoRepository.findById(id);
//        if (produtoOpt.isPresent()){
//            Produto produto = produtoOpt.get();
//            return ResponseEntity.status(200).body(produto);
//        }return ResponseEntity.status(404).build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
//        if (produtoRepository.existsById(id)) {
//            produtoRepository.deleteById(id);
//            return ResponseEntity.status(204).build();
//        }
//
//        return ResponseEntity.status(404).build();
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Produto> atualizar
//            (@PathVariable Integer id, @RequestBody Produto produto){
//        produto.setId(id);
//        Produto produtoSalvo = produtoRepository.save(produto);
//        if (produtoRepository.existsById(id)){
//            return ResponseEntity.status(200).body(produtoSalvo);
//        }
//        return ResponseEntity.status(404).build();
//    }


}
