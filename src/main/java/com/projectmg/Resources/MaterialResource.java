package com.projectmg.Resources;

import com.projectmg.Dtos.MaterialDTO;
import com.projectmg.Enum.TipoAcao;
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
    public ResponseEntity<List<MaterialDTO>> listarAtivos() {
        List<MaterialDTO> materiais = materialService.listarAtivos();
        return ResponseEntity.ok(materiais);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<MaterialDTO>> listarTodos() {
        List<MaterialDTO> materiais = materialService.listarTodos();
        return ResponseEntity.ok(materiais);
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
    public ResponseEntity<Void> deletarMaterial(
            @PathVariable Long id,
            @RequestHeader("usuario") String usuario) { // recebe o header
        materialService.deletarMaterial(id, usuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/adicionar-quantidade/{id}/{qtd}")
    public MaterialDTO adicionar(@PathVariable Long id, @PathVariable int qtd, @RequestBody Map<String, String> body) {
        return materialService.atualizarQuantidade(id, qtd, TipoAcao.ADICIONADO, "sistema", body.get("comentario"));
    }

    @PutMapping("/retirar-quantidade/{id}/{qtd}")
    public MaterialDTO retirar(@PathVariable Long id, @PathVariable int qtd, @RequestBody Map<String, String> body) {
        return materialService.atualizarQuantidade(id, qtd, TipoAcao.RETIRADO, "sistema", body.get("comentario"));
    }

    @PutMapping("/definir-limite/{id}/{limiteMinimo}")
    public ResponseEntity<MaterialDTO> definirLimite(
            @PathVariable Long id,
            @PathVariable Integer limiteMinimo) {
        MaterialDTO atualizado = materialService.definirLimite(id, limiteMinimo);
        return ResponseEntity.ok(atualizado);
    }


}
