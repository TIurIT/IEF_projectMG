package com.projectmg.Controller;

import com.projectmg.Domain.Dto.MaterialDTO;
import com.projectmg.Domain.Dto.VendaReferenciaDTO;
import com.projectmg.Domain.Enum.TipoAcao;
import com.projectmg.Service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mg/estoque")
public class MaterialResource {

    @Autowired
    private MaterialService materialService;

    private String usuarioLogado() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
    
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
            @RequestBody MaterialDTO dto) {

        MaterialDTO criado = materialService.cadastrarMaterial(
                dto,
                usuarioLogado()
        );

        return ResponseEntity.ok(criado);
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<MaterialDTO> atualizarMaterial(
            @PathVariable Long id,
            @RequestBody MaterialDTO dto) {

        return ResponseEntity.ok(
                materialService.atualizarMaterial(
                        id,
                        dto,
                        usuarioLogado()
                )
        );
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarMaterial(@PathVariable Long id) {
        materialService.deletarMaterial(id, usuarioLogado());
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/adicionar-quantidade/{id}/{qtd}")
    public ResponseEntity<MaterialDTO> adicionar(
            @PathVariable Long id,
            @PathVariable Double qtd,
            @RequestBody Map<String, String> body) {

        String comentario = body.getOrDefault(
                "comentario",
                "Entrada de estoque"
        );

        MaterialDTO atualizado = materialService.atualizarQuantidade(
                id,
                qtd,
                TipoAcao.ADICIONADO,
                usuarioLogado(),
                comentario
        );

        return ResponseEntity.ok(atualizado);
    }
    @PutMapping("/retirar-quantidade/{id}/{qtd}")
    public ResponseEntity<MaterialDTO> retirar(
            @PathVariable Long id,
            @PathVariable Double qtd,
            @RequestBody Map<String, String> body) {

        String comentario = body.getOrDefault(
                "comentario",
                "Saída de estoque"
        );

        MaterialDTO atualizado = materialService.atualizarQuantidade(
                id,
                qtd,
                TipoAcao.RETIRADO,
                usuarioLogado(),
                comentario
        );

        return ResponseEntity.ok(atualizado);
    }
    @PutMapping("/definir-limite/{id}/{limiteMinimo}")
    public ResponseEntity<MaterialDTO> definirLimite(
            @PathVariable Long id,
            @PathVariable Double limiteMinimo) {

        return ResponseEntity.ok(
                materialService.definirLimite(id, limiteMinimo)
        );
    }
    @PutMapping("/favorito/{id}")
    public ResponseEntity<MaterialDTO> alternarFavorito(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                materialService.alternarFavorito(id)
        );
    }
    @PutMapping("/reativar/{id}")
    public ResponseEntity<MaterialDTO> reativarMaterial(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                materialService.reativarMaterial(
                        id,
                        usuarioLogado()
                )
        );
    }
    @PutMapping("/programar-compra/{id}")
    public ResponseEntity<MaterialDTO> programarCompra(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String data = body.get("dataProgramadaCompra");

        return ResponseEntity.ok(
                materialService.programarCompra(
                        id,
                        data,
                        usuarioLogado()
                )
        );
    }
    @PostMapping("/retirar-por-referencia")
    public ResponseEntity<MaterialDTO> retirarPorReferencia(
            @RequestBody VendaReferenciaDTO dto) {

        return ResponseEntity.ok(
                materialService.retirarPorReferencia(
                        dto,
                        usuarioLogado()
                )
        );
    }
}
