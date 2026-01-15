package com.projectmg.Controller.Material;

import com.projectmg.Domain.Dto.Material.MaterialDTO;
import com.projectmg.Domain.Dto.Material.VendaReferenciaDTO;
import com.projectmg.Domain.Enum.TipoAcao;
import com.projectmg.Service.Material.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /* ===================== CONSULTAS ===================== */

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public ResponseEntity<List<MaterialDTO>> listarAtivos() {
        return ResponseEntity.ok(materialService.listarAtivos());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/todos")
    public ResponseEntity<List<MaterialDTO>> listarTodos() {
        return ResponseEntity.ok(materialService.listarTodos());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/ultimos")
    public ResponseEntity<List<MaterialDTO>> listarUltimos() {
        return ResponseEntity.ok(materialService.listarUltimos());
    }

    /* ===================== CADASTRO / EDIÇÃO ===================== */

    @PreAuthorize("@autorizacaoService.podeCriarMaterial()")
    @PostMapping("/cadastrar")
    public ResponseEntity<MaterialDTO> cadastrarMaterial(
            @RequestBody MaterialDTO dto) {

        MaterialDTO criado = materialService.cadastrarMaterial(
                dto,
                usuarioLogado()
        );

        return ResponseEntity.ok(criado);
    }

    @PreAuthorize("@autorizacaoService.podeCriarMaterial()")
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

    @PreAuthorize("@autorizacaoService.podeExcluirMaterial()")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarMaterial(@PathVariable Long id) {
        materialService.deletarMaterial(id, usuarioLogado());
        return ResponseEntity.noContent().build();
    }

    /* ===================== MOVIMENTAÇÃO DE ESTOQUE ===================== */

    @PreAuthorize("@autorizacaoService.podeAlterarEstoque()")
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

    @PreAuthorize("@autorizacaoService.podeAlterarEstoque()")
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

    /* ===================== CONFIGURAÇÕES ===================== */

    @PreAuthorize("@autorizacaoService.podeCriarMaterial()")
    @PutMapping("/definir-limite/{id}/{limiteMinimo}")
    public ResponseEntity<MaterialDTO> definirLimite(
            @PathVariable Long id,
            @PathVariable Double limiteMinimo) {

        return ResponseEntity.ok(
                materialService.definirLimite(id, limiteMinimo)
        );
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/favorito/{id}")
    public ResponseEntity<MaterialDTO> alternarFavorito(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                materialService.alternarFavorito(id)
        );
    }

    @PreAuthorize("@autorizacaoService.podeCriarMaterial()")
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

    @PreAuthorize("@autorizacaoService.podeCriarMaterial()")
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

    /* ===================== REFERÊNCIA / VENDA ===================== */

    @PreAuthorize("@autorizacaoService.podeAlterarEstoque()")
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
