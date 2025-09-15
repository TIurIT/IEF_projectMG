package com.projectmg.Resources;



import com.projectmg.Dto.HistoricoMaterialDTO;
import com.projectmg.Services.HistoricoMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // Controller
    @GetMapping("/ultimos")
    public ResponseEntity<List<HistoricoMaterialDTO>> ultimos() {
        List<HistoricoMaterialDTO> ultimos = historicoService.listarUltimosHistoricos();
        return ResponseEntity.ok(ultimos);
    }

    @GetMapping("/item/{id}")
    public List<HistoricoMaterialDTO> historico(@PathVariable Long id) {
        return historicoService.listarHistorico(id);
    }
}
