package com.projectmg.Resources;

import com.projectmg.Dto.TerceirosDTO;
import com.projectmg.Services.TerceirosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mg/terceiros")
public class TerceirosResource {

    @Autowired
    private TerceirosService terceirosService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<TerceirosDTO> buscarTerceirosPorId(@PathVariable Long id){
        return ResponseEntity.ok(terceirosService.buscarTerceirosPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<TerceirosDTO> cadastrarTerceiros(@RequestBody TerceirosDTO TerceirosDto){
        TerceirosDTO Terceiros = terceirosService.cadastrarTerceiros(TerceirosDto);
        return ResponseEntity.ok(terceirosService.cadastrarTerceiros(TerceirosDto));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarTerceiros(@PathVariable Long id) {
        terceirosService.deletarTerceiros(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<TerceirosDTO> atualizarTerceiros(@PathVariable Long id, @RequestBody TerceirosDTO TerceirosDto){
        return ResponseEntity.ok(terceirosService.atualizarTerceiros(TerceirosDto));
    }

    @GetMapping("/b/nome/{nome}")
    public ResponseEntity<TerceirosDTO> buscarTerceirosPorNome(@PathVariable String nome){
        return ResponseEntity.ok(terceirosService.buscarTerceirosPorNome(nome));
    }

    @GetMapping("/b/bairro/{bairro}")
    public ResponseEntity<TerceirosDTO> buscarTerceirosPorBairro(@PathVariable String bairro){
        return ResponseEntity.ok(terceirosService.buscarTerceirosPorBairro(bairro));
    }
}
