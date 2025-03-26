package com.projectmg.Services;

import com.projectmg.Dto.ProdutoDTO;
import com.projectmg.Models.Produto;
import com.projectmg.Repositories.ProdutoRepository;
import com.projectmg.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private static final String MSG_PRODUTO = "Produto não encontrado";

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoDTO converterProdutoParaProdutoDTO(Produto produto){
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produto.getId());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setReferencia(produto.getReferencia());
        return produtoDTO;
    }

    public Produto converterProdutoDTOParaProduto(ProdutoDTO produtoDTO){
        Produto produto = new Produto();
        produto.setId(produtoDTO.getId());
        produto.setNome(produtoDTO.getNome());
        produto.setReferencia(produtoDTO.getReferencia());
        return produto;
    }

    public ProdutoDTO cadastrarProduto(ProdutoDTO produtoDTO){
        Produto produto = converterProdutoDTOParaProduto(produtoDTO);
        produto = produtoRepository.save(produto);
        return converterProdutoParaProdutoDTO(produto);
    }

    public ProdutoDTO atualizarProduto(ProdutoDTO produtoDTO){
        Produto produto = produtoRepository.findById(produtoDTO.getId())
                .orElseThrow(() -> new BusinessException(MSG_PRODUTO));
        produto = converterProdutoDTOParaProduto(produtoDTO);
        produtoRepository.save(produto);
        return converterProdutoParaProdutoDTO(produto);
    }

    public void deletarProduto(Long id){
        produtoRepository.deleteById(id);
    }

    public ProdutoDTO buscarProdutoPorId(Long id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MSG_PRODUTO));
        return converterProdutoParaProdutoDTO(produto);
    }

    public List<ProdutoDTO> buscarProdutoPorNome(String nome){
        List<Produto> produtos = produtoRepository.findByNome(nome);
        if (produtos.isEmpty()) {
            throw new BusinessException(MSG_PRODUTO);
        }
        List<ProdutoDTO> dtos = new java.util.ArrayList<>();
        produtos.forEach(produto -> {
            dtos.add(converterProdutoParaProdutoDTO(produto));
        });

        return dtos;
    }

    public List<ProdutoDTO> buscarProdutoPorReferencia(String referencia){
        List<Produto> produtos = produtoRepository.findByReferencia(referencia);
        if (produtos.isEmpty()) {
            throw new BusinessException(MSG_PRODUTO);
        }
        List<ProdutoDTO> dtos = new java.util.ArrayList<>();
        produtos.forEach(produto -> {
            dtos.add(converterProdutoParaProdutoDTO(produto));
        });

        return dtos;
    }
}
