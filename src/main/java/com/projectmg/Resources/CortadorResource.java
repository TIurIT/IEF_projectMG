package com.projectmg.Resources;

import com.projectmg.Dto.CortadorDto;
import com.projectmg.Models.Cortador;
import com.projectmg.Services.CortadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mg/cortador")
public class CortadorResource {

    @Autowired
    private CortadorService cortadorService;

    @GetMapping("/buscar/{id}")
    public CortadorDto buscarCortadorPorId(@PathVariable Long id){
        return cortadorService.buscarCortadorPorId(id);
    }

    @PostMapping("/cadastrar")
    public CortadorDto cadastrarCortador(@RequestBody CortadorDto cortadorDto){
        return cortadorService.cadastrarCortador(cortadorDto);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarCortador(@PathVariable Long id) {
        cortadorService.deletarCortador(id);
    }

    @PutMapping("/atualizar/{id}")
    public CortadorDto atualizarCortador(@PathVariable Long id, @RequestBody CortadorDto cortadorDto){
        return cortadorService.atualizarCortador(cortadorDto);
    }

    @GetMapping("/buscar/bairro/{bairro}")
    public List<Cortador> buscarCortadorPorBairro(@PathVariable String bairro){
        return cortadorService.buscarCortadorPorBairro(bairro);
    }
}
