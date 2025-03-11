package com.projectmg.Resources;

import com.projectmg.DTO.EstoqueDto;
import com.projectmg.Models.Estoque;
import com.projectmg.Services.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/mg/estoque")
public class EstoqueResource {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<EstoqueDto> buscarEstoquePorId(@PathVariable Long id){
        return ResponseEntity.ok(estoqueService.buscarEstoque(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<EstoqueDto> cadastrarEstoque(@RequestBody EstoqueDto EstoqueDto){
        EstoqueDto estoque = estoqueService.cadastrarEstoque(EstoqueDto);
        return ResponseEntity.ok(estoqueService.cadastrarEstoque(estoque));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarEstoque(@PathVariable Long id) {
        estoqueService.excluirEstoque(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EstoqueDto> atualizarEstoque(@PathVariable Long id, @RequestBody EstoqueDto EstoqueDto){
        return ResponseEntity.ok(estoqueService.atualizarEstoque(EstoqueDto));
    }

    @GetMapping("/buscar/tipo/{tipo}")
    public ResponseEntity<EstoqueDto> buscarEstoquePorTipo(@PathVariable String tipo){
        return ResponseEntity.ok(estoqueService.buscarEstoquePorTipo(tipo));
    }

    @GetMapping("/buscar/nome/{nome}")
    public ResponseEntity<EstoqueDto> buscarEstoquePorNome(@PathVariable String nome){
        return ResponseEntity.ok(estoqueService.buscarEstoquePorNome(nome));
    }

    @GetMapping("/buscar/marca/{marca}")
    public ResponseEntity<EstoqueDto> buscarEstoquePorMarca(@PathVariable String marca){
        return ResponseEntity.ok(estoqueService.buscarEstoquePorMarca(marca));
    }

    @GetMapping("/buscar/nomeAll/{nome}")
    public ResponseEntity<List<Estoque>> findAllByNome(@PathVariable String nome){
        return ResponseEntity.ok(estoqueService.findAllByNome(nome));
    }

    @GetMapping("/buscar/tipoAll/{tipo}")
    public ResponseEntity<List<Estoque>> findAllByTipo(@PathVariable String tipo){
        return ResponseEntity.ok(estoqueService.findAllByTipo(tipo));
    }

    @GetMapping("/buscar/marcaAll/{marca}")
    public ResponseEntity<List<Estoque>> findAllByMarca(@PathVariable String marca){
        return ResponseEntity.ok(estoqueService.findAllByMarca(marca));
    }
}
