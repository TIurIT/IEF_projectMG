package com.projectmg.Resources;

import com.projectmg.Dtos.ReferenciaDTO;
import com.projectmg.Services.ReferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mg/referencia")
public class ReferenciaResource {

    @Autowired
    private ReferenciaService referenciaService;

    /**
     * 🔍 Buscar referências
     * @param somenteAtivos true = apenas ativos | false = ativos + inativos
     */
    @GetMapping
    public ResponseEntity<List<ReferenciaDTO>> buscarTodos(
            @RequestParam(defaultValue = "true") boolean somenteAtivos
    ) {
        return ResponseEntity.ok(
                referenciaService.buscarReferencias(somenteAtivos)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReferenciaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                referenciaService.buscarReferenciaPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<ReferenciaDTO> cadastrar(
            @RequestBody ReferenciaDTO dto
    ) {
        return ResponseEntity.ok(
                referenciaService.cadastrarReferencia(dto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReferenciaDTO> atualizar(
            @PathVariable Long id,
            @RequestBody ReferenciaDTO dto
    ) {

        ReferenciaDTO dtoComId = new ReferenciaDTO(
                id,
                dto.nome(),
                dto.referencia(),
                dto.rendimento(),
                dto.dataAtualizacao(),
                dto.usuarioUltimaAlteracao(),
                dto.acao(),
                dto.ativo()
        );

        return ResponseEntity.ok(
                referenciaService.atualizarReferencia(dtoComId)
        );
    }

    /**
     * ❌ Exclusão lógica (inativar)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        referenciaService.deletarReferencia(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * ♻ Reativar referência
     */
    @PutMapping("/{id}/reativar")
    public ResponseEntity<Void> reativar(@PathVariable Long id) {
        referenciaService.reativarReferencia(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/nome")
    public ResponseEntity<List<ReferenciaDTO>> buscarPorNome(
            @RequestParam String nome
    ) {
        return ResponseEntity.ok(
                referenciaService.buscarReferenciaPorNome(nome)
        );
    }

    @GetMapping("/buscar/ref")
    public ResponseEntity<List<ReferenciaDTO>> buscarPorReferencia(
            @RequestParam String ref
    ) {
        return ResponseEntity.ok(
                referenciaService.buscarReferenciaPorReferencia(ref)
        );
    }

    /**
     * 🕒 Últimas alterações (ativos + inativos)
     */
    @GetMapping("/ultimos")
    public ResponseEntity<List<ReferenciaDTO>> listarUltimos() {
        return ResponseEntity.ok(
                referenciaService.buscarUltimosAtualizados()
        );
    }
}
