package com.projectmg.Resources;

import com.projectmg.Dtos.ReferenciaDTO;
import com.projectmg.Models.Referencia;
import com.projectmg.Repositories.ReferenciaRepository;
import com.projectmg.Services.ReferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mg/produto")
public class ReferenciaResource {

    @Autowired
    private ReferenciaService referenciaService;

    @Autowired
    private ReferenciaRepository referenciaRepository;

    @GetMapping({"/", ""})
    public ResponseEntity<List<ReferenciaDTO>> buscarTodosProdutos() {
        return ResponseEntity.ok(referenciaService.buscarProdutoTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ReferenciaDTO> buscarProdutoPorId(@PathVariable Long id){
        return ResponseEntity.ok(referenciaService.buscarProdutoPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ReferenciaDTO> cadastrarProduto(@RequestBody ReferenciaDTO referenciaDTO){
        referenciaDTO = referenciaService.cadastrarProduto(referenciaDTO);
        return ResponseEntity.ok(referenciaDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        referenciaService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ReferenciaDTO> atualizarProduto(@PathVariable Long id, @RequestBody ReferenciaDTO referenciaDTO){
        referenciaDTO.setId(id);
        referenciaDTO = referenciaService.atualizarProduto(referenciaDTO);
        return ResponseEntity.ok(referenciaDTO);
    }

    @GetMapping("/b/nome/{nome}")
    public ResponseEntity<List<ReferenciaDTO>> buscarProdutoPorNome(@PathVariable String nome){
        return ResponseEntity.ok(referenciaService.buscarProdutoPorNome(nome));
    }

    @GetMapping("/b/ref/{referencia}")
    public ResponseEntity<List<ReferenciaDTO>> buscarProdutoPorReferencia(@PathVariable String referencia){
        return ResponseEntity.ok(referenciaService.buscarProdutoPorReferencia(referencia));
    }

    @GetMapping("/ultimos")
    public List<Referencia> listarUltimosProdutos() {
        return referenciaRepository.findTop5ByOrderByDataAtualizacaoDesc();
    }
}
