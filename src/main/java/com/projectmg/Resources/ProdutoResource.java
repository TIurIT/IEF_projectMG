package com.projectmg.Resources;

import com.projectmg.Dto.ProdutoDTO;
import com.projectmg.Services.ProdutoService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mg/produto")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping({"/", ""})
    public ResponseEntity<List<ProdutoDTO>> getAllProdutos() {
        return ResponseEntity.ok(produtoService.buscarProdutoTodos());
    }

    @RequestMapping("/buscar/{id}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable Long id){
        produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(id));
    }

    @RequestMapping("/cadastrar")
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody ProdutoDTO produtoDTO){
        produtoDTO = produtoService.cadastrarProduto(produtoDTO);
        return ResponseEntity.ok(produtoDTO);
    }

    @RequestMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping("/atualizar/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO){
        produtoDTO = produtoService.atualizarProduto(produtoDTO);
        return ResponseEntity.ok(produtoService.atualizarProduto(produtoDTO));
    }

    @RequestMapping("/b/nome/{nome}")
    public ResponseEntity<List<ProdutoDTO>> buscarProdutoPorNome(@PathVariable String nome){
        produtoService.buscarProdutoPorNome(nome);
        return ResponseEntity.ok(produtoService.buscarProdutoPorNome(nome));
    }

    @RequestMapping("/b/ref/{referencia}")
    public ResponseEntity<List<ProdutoDTO>> buscarProdutoPorReferencia(@PathVariable String referencia){
        produtoService.buscarProdutoPorReferencia(referencia);
        return ResponseEntity.ok(produtoService.buscarProdutoPorReferencia(referencia));
    }
}
