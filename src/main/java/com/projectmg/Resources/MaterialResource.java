package com.projectmg.Resources;

import com.projectmg.Dtos.MaterialDTO;
import com.projectmg.Dtos.VendaReferenciaDTO;
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
        return ResponseEntity.ok(materialService.listarAtivos());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<MaterialDTO>> listarTodos() {
        return ResponseEntity.ok(materialService.listarTodos());
    }

    @GetMapping("/ultimos")
    public ResponseEntity<List<MaterialDTO>> listarUltimos() {
        return ResponseEntity.ok(materialService.listarUltimos());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<MaterialDTO> cadastrarMaterial(
            @RequestBody MaterialDTO dto,
            @RequestHeader("usuario") String usuario) {

        MaterialDTO criado = materialService.cadastrarMaterial(dto, usuario);
        return ResponseEntity.ok(criado);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<MaterialDTO> atualizarMaterial(
            @PathVariable Long id,
            @RequestBody MaterialDTO dto,
            @RequestHeader("usuario") String usuario) {

        return ResponseEntity.ok(materialService.atualizarMaterial(id, dto, usuario));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarMaterial(
            @PathVariable Long id,
            @RequestHeader("usuario") String usuario) {

        materialService.deletarMaterial(id, usuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/adicionar-quantidade/{id}/{qtd}")
    public ResponseEntity<MaterialDTO> adicionar(
            @PathVariable Long id,
            @PathVariable Double qtd,
            @RequestBody Map<String, String> body,
            @RequestHeader("usuario") String usuario) {

        String comentario = body.getOrDefault("comentario", "Entrada de estoque");
        MaterialDTO atualizado = materialService.atualizarQuantidade(id, qtd, TipoAcao.ADICIONADO, usuario, comentario);

        return ResponseEntity.ok(atualizado);
    }

    @PutMapping("/retirar-quantidade/{id}/{qtd}")
    public ResponseEntity<MaterialDTO> retirar(
            @PathVariable Long id,
            @PathVariable Double qtd,
            @RequestBody Map<String, String> body,
            @RequestHeader("usuario") String usuario) {

        String comentario = body.getOrDefault("comentario", "Saída de estoque");
        MaterialDTO atualizado = materialService.atualizarQuantidade(id, qtd, TipoAcao.RETIRADO, usuario, comentario);

        return ResponseEntity.ok(atualizado);
    }

    @PutMapping("/definir-limite/{id}/{limiteMinimo}")
    public ResponseEntity<MaterialDTO> definirLimite(
            @PathVariable Long id,
            @PathVariable Double limiteMinimo) {

        return ResponseEntity.ok(materialService.definirLimite(id, limiteMinimo));
    }

    @PutMapping("/reativar/{id}")
    public ResponseEntity<MaterialDTO> reativarMaterial(
            @PathVariable Long id,
            @RequestHeader("usuario") String usuario) {

        return ResponseEntity.ok(materialService.reativarMaterial(id, usuario));
    }

    @PutMapping("/programar-compra/{id}")
    public ResponseEntity<MaterialDTO> programarCompra(
            @PathVariable Long id,
            @RequestBody Map<String, String> body,
            @RequestHeader("usuario") String usuario) {

        String data = body.get("dataProgramadaCompra");

        MaterialDTO atualizado = materialService.programarCompra(id, data, usuario);

        return ResponseEntity.ok(atualizado);
    }

    @PutMapping("/favorito/{id}")
    public ResponseEntity<MaterialDTO> alternarFavorito(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.alternarFavorito(id));
    }

    @PostMapping("/retirar-por-referencia")
    public ResponseEntity<MaterialDTO> retirarPorReferencia(
            @RequestBody VendaReferenciaDTO dto,
            @RequestHeader("usuario") String usuario) {

        return ResponseEntity.ok(materialService.retirarPorReferencia(dto, usuario));
    }

}
