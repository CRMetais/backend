package school.sptech.cr_metais.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.PrecoProdutoTabela.PrecoProdutoTabelaRequestDto;
import school.sptech.cr_metais.dto.PrecoProdutoTabela.PrecoProdutoTabelaResponseDto;
import school.sptech.cr_metais.entity.ItemPedidoCompra;
import school.sptech.cr_metais.entity.PrecoProdutoTabela;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.service.PrecoProdutoTabelaService;

import java.util.List;

@RestController
@RequestMapping("/preco-produtos-tabelas")
public class PrecoProdutoTabelaController {

    private final PrecoProdutoTabelaService precoProdutoTabelaService;

    public PrecoProdutoTabelaController(PrecoProdutoTabelaService precoProdutoTabelaService){
        this.precoProdutoTabelaService = precoProdutoTabelaService;
    }

    @PostMapping
    public ResponseEntity<PrecoProdutoTabelaResponseDto> cadastrar(@RequestBody PrecoProdutoTabelaRequestDto dto){
        PrecoProdutoTabelaResponseDto resposta = precoProdutoTabelaService.criarPrecoProduto(dto);
        return ResponseEntity.status(201).body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<PrecoProdutoTabelaResponseDto>> listar(){
        List<PrecoProdutoTabelaResponseDto> todos = precoProdutoTabelaService.listarTodos();
        if (todos.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrecoProdutoTabelaResponseDto> buscarPorId(@PathVariable Integer id){
        PrecoProdutoTabelaResponseDto dto = precoProdutoTabelaService.buscarPorId(id);
        return ResponseEntity.status(200).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        precoProdutoTabelaService.deletar(id);
        return ResponseEntity.status(204).build();
    }

}
