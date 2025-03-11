package com.projectmg.Resources;

import com.projectmg.DTO.SublimacaoDto;
import com.projectmg.Models.Sublimacao;
import com.projectmg.Services.SublimacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mg/sublimacaos")
public class SublimacaoResource {

    @Autowired
    private SublimacaoService sublimacaoService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<SublimacaoDto> buscarSublimacaoPorId(@PathVariable Long id){
        return ResponseEntity.ok(sublimacaoService.buscarSublimacaoPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<SublimacaoDto> cadastrarSublimacao(@RequestBody SublimacaoDto sublimacaoDto){
        return ResponseEntity.ok(sublimacaoService.cadastrarSublimacao(sublimacaoDto));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarSublimacao(@PathVariable Long id) {
        sublimacaoService.deletarSublimacao(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<SublimacaoDto> atualizarSublimacao(@PathVariable Long id, @RequestBody SublimacaoDto sublimacaoDto){
        return ResponseEntity.ok(sublimacaoService.atualizarSublimacao(sublimacaoDto));
    }

    @GetMapping("/buscar/bairro/{bairro}")
    public ResponseEntity<List<Sublimacao>> buscarSublimacaoPorBairro(@PathVariable String bairro){
        return ResponseEntity.ok(sublimacaoService.buscarSublimacaoPorBairro(bairro));
    }
}
