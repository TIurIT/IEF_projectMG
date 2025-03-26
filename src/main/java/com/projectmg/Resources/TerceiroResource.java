package com.projectmg.Resources;

import com.projectmg.Dto.TerceiroDTO;
import com.projectmg.Enum.Servico;
import com.projectmg.Services.TerceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mg/terceiro")
public class TerceiroResource {

    @Autowired
    private TerceiroService terceiroService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<TerceiroDTO> buscarTerceiroPorId(@PathVariable Long id){
        return ResponseEntity.ok(terceiroService.buscarTerceiroPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<TerceiroDTO> cadastrarTerceiro(@RequestBody TerceiroDTO terceiroDto){
        return ResponseEntity.ok(terceiroService.cadastrarTerceiro(terceiroDto));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarTerceiro(@PathVariable Long id) {
        terceiroService.deletarTerceiro(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<TerceiroDTO> atualizarTerceiro(@PathVariable Long id, @RequestBody TerceiroDTO terceiroDto){
        return ResponseEntity.ok(terceiroService.atualizarTerceiro(terceiroDto));
    }

    @GetMapping("/b/nome/{nome}")
    public ResponseEntity<List<TerceiroDTO>> buscarTerceiroPorNome(@PathVariable String nome){
        return ResponseEntity.ok(terceiroService.buscarTerceiroPorNome(nome));
    }

    @GetMapping("/b/bairro/{bairro}")
    public ResponseEntity<List<TerceiroDTO>> buscarTerceiroPorBairro(@PathVariable String bairro){
        return ResponseEntity.ok(terceiroService.buscarTerceiroPorBairro(bairro));
    }

}
