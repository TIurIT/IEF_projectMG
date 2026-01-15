package com.projectmg.Controller.Material;



import com.projectmg.Domain.Dto.Material.HistoricoMaterialDTO;
import com.projectmg.Service.Material.HistoricoMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mg/estoque/historico")
public class HistoricoMaterialResource {

    @Autowired
    private HistoricoMaterialService historicoService;


    @GetMapping("/item/{id}")
    public List<HistoricoMaterialDTO> getHistoricoMaterial(@PathVariable Long id) {
        return historicoService.listarHistoricoPorMaterial(id);
    }

    // 🔹 Histórico geral
    @GetMapping("/todos")
    public List<HistoricoMaterialDTO> getHistoricoGeral() {
        return historicoService.listarHistoricoGeral();
    }
}
