package com.projectmg.Resources;

import com.projectmg.Dto.HistoricoMaterialDTO;
import com.projectmg.Dto.MaterialDTO;
import com.projectmg.Enum.TipoAcao;
import com.projectmg.Models.HistoricoMaterial;
import com.projectmg.Models.Material;
import com.projectmg.Repositories.HistoricoMaterialRepository;
import com.projectmg.Repositories.MaterialRepository;
import com.projectmg.Services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/mg/estoque")
public class MaterialResource {

    @Autowired
    private MaterialService materialService;

    @GetMapping("/")
    public List<MaterialDTO> listarTodos() {
        return materialService.listarTodos();
    }

    @GetMapping("/ultimos")
    public List<MaterialDTO> listarUltimos() {
        return materialService.listarUltimos();
    }

    @PostMapping("/cadastrar")
    public MaterialDTO cadastrarMaterial(@RequestBody MaterialDTO dto) {
        return materialService.cadastrarMaterial(dto, "Sistema"); // aqui você pode puxar do usuário logado
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<MaterialDTO> atualizarMaterial(
            @PathVariable Long id,
            @RequestBody MaterialDTO dto,
            @RequestHeader("usuario") String usuario) {
        MaterialDTO atualizado = materialService.atualizarMaterial(id, dto, usuario);
        return ResponseEntity.ok(atualizado);
    }


    @DeleteMapping("/deletar/{id}")
    public void deletarMaterial(@PathVariable Long id) {
        materialService.deletarMaterial(id);
    }

    @PutMapping("/adicionar-quantidade/{id}/{qtd}")
    public MaterialDTO adicionar(@PathVariable Long id, @PathVariable int qtd, @RequestBody Map<String, String> body) {
        return materialService.atualizarQuantidade(id, qtd, TipoAcao.ADICIONADO, "sistema", body.get("comentario"));
    }

    @PutMapping("/retirar-quantidade/{id}/{qtd}")
    public MaterialDTO retirar(@PathVariable Long id, @PathVariable int qtd, @RequestBody Map<String, String> body) {
        return materialService.atualizarQuantidade(id, qtd, TipoAcao.RETIRADO, "sistema", body.get("comentario"));
    }

    @GetMapping("/item/historico/{id}")
    public List<HistoricoMaterialDTO> historico(@PathVariable Long id) {
        return materialService.listarHistorico(id);
    }
}
