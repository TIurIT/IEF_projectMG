package com.projectmg.Resources;

import com.projectmg.Dtos.ReferenciaDTO;
import com.projectmg.Services.ReferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mg/referencias")
public class ReferenciaResource {

    @Autowired
    private ReferenciaService referenciaService;

    // GET - Listar todos
    @GetMapping
    public ResponseEntity<List<ReferenciaDTO>> buscarTodos() {
        return ResponseEntity.ok(referenciaService.buscarReferenciaTodos());
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReferenciaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(referenciaService.buscarReferenciaPorId(id));
    }

    // POST - Cadastrar
    @PostMapping
    public ResponseEntity<ReferenciaDTO> cadastrar(@RequestBody ReferenciaDTO dto) {
        return ResponseEntity.ok(referenciaService.cadastrarReferencia(dto));
    }

    // PUT - Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<ReferenciaDTO> atualizar(@PathVariable Long id,
                                                   @RequestBody ReferenciaDTO dto) {

        ReferenciaDTO dtoComId = new ReferenciaDTO(
                id,
                dto.nome(),
                dto.referencia(),
                dto.rendimento(),
                dto.dataAtualizacao(),
                dto.usuarioUltimaAlteracao(),
                dto.acao()
        );

        return ResponseEntity.ok(referenciaService.atualizarReferencia(dtoComId));
    }

    // DELETE - Remover
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        referenciaService.deletarReferencia(id);
        return ResponseEntity.noContent().build();
    }

    // GET - Buscar por nome (ex: /mg/referencias?nome=Camisa)
    @GetMapping("/buscar/nome")
    public ResponseEntity<List<ReferenciaDTO>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(referenciaService.buscarReferenciaPorNome(nome));
    }

    // GET - Buscar por referencia (ex: /mg/referencias?ref=123-ABC)
    @GetMapping("/buscar/ref")
    public ResponseEntity<List<ReferenciaDTO>> buscarPorReferencia(@RequestParam String ref) {
        return ResponseEntity.ok(referenciaService.buscarReferenciaPorReferencia(ref));
    }

    // GET - Últimos atualizados
    @GetMapping("/ultimos")
    public ResponseEntity<List<ReferenciaDTO>> listarUltimos() {
        return ResponseEntity.ok(referenciaService.buscarUltimosAtualizados());
    }
}
