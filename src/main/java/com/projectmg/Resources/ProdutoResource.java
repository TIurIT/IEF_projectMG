package com.projectmg.Resources;

import com.projectmg.Dtos.ProdutoDTO;
import com.projectmg.Models.Produto;
import com.projectmg.Repositories.ProdutoRepository;
import com.projectmg.Services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mg/produto")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping({"/", ""})
    public ResponseEntity<List<ProdutoDTO>> buscarTodosProdutos() {
        return ResponseEntity.ok(produtoService.buscarProdutoTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody ProdutoDTO produtoDTO){
        produtoDTO = produtoService.cadastrarProduto(produtoDTO);
        return ResponseEntity.ok(produtoDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO){
        produtoDTO.setId(id);
        produtoDTO = produtoService.atualizarProduto(produtoDTO);
        return ResponseEntity.ok(produtoDTO);
    }

    @GetMapping("/b/nome/{nome}")
    public ResponseEntity<List<ProdutoDTO>> buscarProdutoPorNome(@PathVariable String nome){
        return ResponseEntity.ok(produtoService.buscarProdutoPorNome(nome));
    }

    @GetMapping("/b/ref/{referencia}")
    public ResponseEntity<List<ProdutoDTO>> buscarProdutoPorReferencia(@PathVariable String referencia){
        return ResponseEntity.ok(produtoService.buscarProdutoPorReferencia(referencia));
    }

    @GetMapping("/ultimos")
    public List<Produto> listarUltimosProdutos() {
        return produtoRepository.findTop5ByOrderByDataAtualizacaoDesc();
    }
}
