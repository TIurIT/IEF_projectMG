package com.projectmg.Resources;

import com.projectmg.DTO.EstampariaDto;
import com.projectmg.Services.EstampariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/mg/estamparias")
public class EstampariaResource {

    @Autowired
    private EstampariaService estampariaService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<EstampariaDto> buscarEstampariaPorId(@PathVariable Long id){
        return ResponseEntity.ok(estampariaService.buscarEstampariaPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<EstampariaDto> cadastrarEstamparia(@RequestBody EstampariaDto estampariaDto){
        return ResponseEntity.ok(estampariaService.cadastrarEstamparia(estampariaDto));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarEstamparia(@PathVariable Long id) {
        estampariaService.deletarEstamparia(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EstampariaDto> atualizarEstamparia(@PathVariable Long id, @RequestBody EstampariaDto estampariaDto){
        return ResponseEntity.ok(estampariaService.atualizarEstamparia(estampariaDto));
    }

    @GetMapping("/buscar/bairro/{bairro}")
    public ResponseEntity<EstampariaDto> buscarEstampariaPorBairro(@PathVariable String bairro){
        return ResponseEntity.ok(estampariaService.buscarEstampariaPorBairro(bairro));
    }
}
