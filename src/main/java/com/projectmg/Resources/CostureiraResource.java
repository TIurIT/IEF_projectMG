package com.projectmg.Resources;

import com.projectmg.Dto.CostureiraDto;
import com.projectmg.Models.Costureira;
import com.projectmg.Services.CostureiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mg/costureiras")
public class CostureiraResource {

    @Autowired
    private CostureiraService costureiraService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<CostureiraDto> buscarCostureiraPorId(@PathVariable Long id){
        return ResponseEntity.ok(costureiraService.buscarCostureiraPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CostureiraDto> cadastrarCostureira(@RequestBody CostureiraDto costureiraDto){
        return ResponseEntity.ok(costureiraService.cadastrarCostureira(costureiraDto));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCostureira(@PathVariable Long id) {
        costureiraService.deletarCostureira(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<CostureiraDto> atualizarCostureira(@PathVariable Long id, @RequestBody CostureiraDto costureiraDto){
        return ResponseEntity.ok(costureiraService.atualizarCostureira(costureiraDto));
    }

    @GetMapping("/buscar/bairro/{bairro}")
    public ResponseEntity<List<Costureira>> buscarCostureiraPorBairro(@PathVariable String bairro){
        return ResponseEntity.ok(costureiraService.buscarCostureiraPorBairro(bairro));
    }
}
