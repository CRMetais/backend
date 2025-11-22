package school.sptech.cr_metais.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.PrecoProdutoTabela.PrecoProdutoTabelaRequestDto;
import school.sptech.cr_metais.dto.PrecoProdutoTabela.PrecoProdutoTabelaResponseDto;
import school.sptech.cr_metais.entity.ItemPedidoCompra;
import school.sptech.cr_metais.entity.PrecoProdutoTabela;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.mappers.PrecoProdutoTabelaMapper;
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

        PrecoProdutoTabela precoParaRegistrar = PrecoProdutoTabelaMapper.toEntity(dto);
        PrecoProdutoTabela precoRegistrado = precoProdutoTabelaService.cadastrar(precoParaRegistrar, dto.getIdTabelaPreco(), dto.getIdProduto());
        PrecoProdutoTabelaResponseDto response = PrecoProdutoTabelaMapper.toResponse(precoRegistrado);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrecoProdutoTabelaResponseDto> buscarPorId(@PathVariable Integer id){

        PrecoProdutoTabela precoProdutoTabela = precoProdutoTabelaService.buscarPorId(id);
        PrecoProdutoTabelaResponseDto response = PrecoProdutoTabelaMapper.toResponse(precoProdutoTabela);

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PrecoProdutoTabelaResponseDto>> listar(){

        List<PrecoProdutoTabela> todos = precoProdutoTabelaService.listar();

        if (todos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<PrecoProdutoTabelaResponseDto> response = PrecoProdutoTabelaMapper.toResponse(todos);
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrecoProdutoTabelaResponseDto> atualizar(@PathVariable Integer id, @RequestBody PrecoProdutoTabelaRequestDto dto){

        PrecoProdutoTabela entity = PrecoProdutoTabelaMapper.toEntity(dto);
        entity.setId(id);

        PrecoProdutoTabela precoProdutoTabelaAtualizado = precoProdutoTabelaService.atualizar(entity);
        PrecoProdutoTabelaResponseDto response = PrecoProdutoTabelaMapper.toResponse(precoProdutoTabelaAtualizado);
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){

        precoProdutoTabelaService.deletarPorId(id);
        return ResponseEntity.status(204).build();
    }

}
