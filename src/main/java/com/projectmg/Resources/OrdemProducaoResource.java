package com.projectmg.Resources;

import com.projectmg.Dto.OrdemProducaoDTO;
import com.projectmg.Services.OrdemProducaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordem-producao")
public class OrdemProducaoResource {

    @Autowired
    private OrdemProducaoService ordemProducaoService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<OrdemProducaoDTO> buscarOrdemProducaoPorId(@PathVariable Long id){
        return ResponseEntity.ok(ordemProducaoService.buscarOrdemProducaoPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<OrdemProducaoDTO> cadastrarOrdemProducao(@RequestBody OrdemProducaoDTO ordemProducaoDTO){
        OrdemProducaoDTO ordemProducao = ordemProducaoService.cadastrarOrdemProducao(ordemProducaoDTO);
        return ResponseEntity.ok(ordemProducao);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarOrdemProducao(@PathVariable Long id){
        ordemProducaoService.deletarOrdemProducao(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<OrdemProducaoDTO> atualizarOrdemProducao(@PathVariable Long id, @RequestBody OrdemProducaoDTO ordemProducaoDTO){
        return ResponseEntity.ok(ordemProducaoService.atualizarOrdemProducao(ordemProducaoDTO));
    }

    @GetMapping("/buscar/{clienteId}")
    public ResponseEntity<List<OrdemProducaoDTO>> buscarOrdemProducaoPorCliente(@PathVariable Long clienteId){
        return ResponseEntity.ok(ordemProducaoService.buscarOrdemProducaoPorCliente(clienteId));
    }
}
