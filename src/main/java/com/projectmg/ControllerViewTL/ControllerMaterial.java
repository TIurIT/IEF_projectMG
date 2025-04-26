package com.projectmg.ControllerViewTL;

import com.projectmg.Dto.MaterialDTO;
import com.projectmg.Services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/mg/estoque123")
public class ControllerMaterial {


    @Autowired
    private MaterialService materialService;

    @GetMapping("/listar")
    public String listarMateriais(Model model) {
        List<MaterialDTO> materiais = materialService.buscarMaterials();
        model.addAttribute("material", materiais);
        return "estoque/listar"; // Isso deve bater com o caminho do HTML dentro de src/main/resources/templates
    }
}
