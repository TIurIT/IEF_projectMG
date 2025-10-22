package com.projectmg.Resources;

import com.projectmg.Dtos.OrdemProducaoItemDTO;
import com.projectmg.Services.OrdemProducaoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mg/op/item")
public class OrdemProducaoItemResource {

    @Autowired
    private OrdemProducaoItemService ordemProducaoItemService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<OrdemProducaoItemDTO> buscarOrdemProducaoPorId(@PathVariable Long id){
        return ResponseEntity.ok(ordemProducaoItemService.buscarOrdemProducaoItemPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<OrdemProducaoItemDTO> cadastrarOrdemProducao(@RequestBody OrdemProducaoItemDTO ordemProducaoItemDTO){
        OrdemProducaoItemDTO ordemProducao = ordemProducaoItemService.cadastrarOrdemProducaoItem(ordemProducaoItemDTO);
        return ResponseEntity.ok(ordemProducao);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarOrdemProducao(@PathVariable Long id){
        ordemProducaoItemService.deletarOrdemProducaoItem(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<OrdemProducaoItemDTO> atualizarOrdemProducao(@PathVariable Long id, @RequestBody OrdemProducaoItemDTO ordemProducaoItemDTO){
        return ResponseEntity.ok(ordemProducaoItemService.atualizarOrdemProducaoItem(ordemProducaoItemDTO));
    }
    
}
