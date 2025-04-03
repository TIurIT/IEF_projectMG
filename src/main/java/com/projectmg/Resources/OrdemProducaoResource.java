package com.projectmg.Resources;

import com.projectmg.Dto.OrdemProducaoDTO;
import com.projectmg.Services.OrdemProducaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mg/op")
public class OrdemProducaoResource {

    @Autowired
    OrdemProducaoService ordemProducaoService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<OrdemProducaoDTO> buscarOrdemProducaoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ordemProducaoService.buscarOrdemProducaoPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<OrdemProducaoDTO> cadastrarOrdemProducao(@RequestBody OrdemProducaoDTO ordemProducaoDTO) {
        return ResponseEntity.ok(ordemProducaoService.cadastrarOrdemProducao(ordemProducaoDTO));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<OrdemProducaoDTO> atualizarOrdemProducao(@PathVariable Long id, @RequestBody OrdemProducaoDTO ordemProducaoDTO) {
        return ResponseEntity.ok(ordemProducaoService.atualizarOrdemProducao(ordemProducaoDTO));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarOrdemProducao(@PathVariable Long id) {
        ordemProducaoService.deletarOrdemProducao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/b/cliente/{nome}")
    public ResponseEntity<List<OrdemProducaoDTO>> buscarOrdemProducaoPorCliente(@PathVariable String nome) {
        List<OrdemProducaoDTO> list = ordemProducaoService.buscarOrdemProducaoPorCliente(nome);
        return ResponseEntity.ok(list);
    }
}
