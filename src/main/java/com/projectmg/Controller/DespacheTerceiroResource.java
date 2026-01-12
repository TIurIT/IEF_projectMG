package com.projectmg.Controller;

import com.projectmg.Domain.Entity.DespacheTerceiro;
import com.projectmg.Service.DespacheTerceiroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mg/despache")
@CrossOrigin("*")
public class DespacheTerceiroResource {

    private final DespacheTerceiroService service;

    public DespacheTerceiroResource(DespacheTerceiroService service) {
        this.service = service;
    }

    @PostMapping("/enviar")
    public DespacheTerceiro enviar(
            @RequestParam Long terceiroId,
            @RequestParam Long materialId,
            @RequestParam Double quantidade
    ) {
        return service.despachar(terceiroId, materialId, quantidade);
    }

    @PostMapping("/retorno/{id}")
    public DespacheTerceiro registrarRetorno(
            @PathVariable Long id,
            @RequestParam Double quantidadeRetorno
    ) {
        return service.registrarRetorno(id, quantidadeRetorno);
    }

    // 🔥 LISTAR TODOS OS DESPACHES
    @GetMapping("/all")
    public List<DespacheTerceiro> listarTodos() {
        return service.listarTodos();
    }

    // 🔥 LISTAR POR TERCEIRO
    @GetMapping("/porTerceiro/{id}")
    public List<DespacheTerceiro> listarPorTerceiro(@PathVariable Long id) {
        return service.listarPorTerceiro(id);
    }
}
