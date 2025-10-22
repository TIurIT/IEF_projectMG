package com.projectmg.Resources;


import com.projectmg.Dtos.OrdemProducaoTerceiroDTO;
import com.projectmg.Services.OrdemProducaoTerceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mg/op/terceiro")
public class OrdemProducaoTerceiroResource {

    @Autowired
    OrdemProducaoTerceiroService ordemProducaoTerceiroService;


    @GetMapping("/buscar/{id}")
    public ResponseEntity<OrdemProducaoTerceiroDTO> buscarOrdemProducaoTerceiro(@PathVariable Long id) {
        return ResponseEntity.ok(ordemProducaoTerceiroService.buscarOPT(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<OrdemProducaoTerceiroDTO> cadastrarOPT(@RequestBody OrdemProducaoTerceiroDTO ordemProducaoTerceiroDTO) {
        OrdemProducaoTerceiroDTO ordemProducaoTerceiro = ordemProducaoTerceiroService.cadastrarOPT(ordemProducaoTerceiroDTO);
        return ResponseEntity.ok(ordemProducaoTerceiro);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<OrdemProducaoTerceiroDTO> atualizarOPT(@PathVariable Long id,@RequestBody OrdemProducaoTerceiroDTO ordemProducaoTerceiroDTO) {
        return ResponseEntity.ok(ordemProducaoTerceiroService.atualizarOPT(ordemProducaoTerceiroDTO));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<OrdemProducaoTerceiroDTO> deletarOPT(@PathVariable Long id) {
        ordemProducaoTerceiroService.deletarOPT(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/terceiro")
    public ResponseEntity<List<OrdemProducaoTerceiroDTO>> buscarOrdemPorTerceiro(@RequestParam String nome) {
        return  ResponseEntity.ok(ordemProducaoTerceiroService.buscarOrdemPorTerceiro(nome));
    }
}
