package com.projectmg.Services;

import com.projectmg.Dto.ProdutoDTO;
import com.projectmg.Enum.TipoAcao;
import com.projectmg.Models.Produto;
import com.projectmg.Repositories.ProdutoRepository;
import com.projectmg.Security.UsuarioAuditoria;
import com.projectmg.Specs.ProdutoSpec;
import com.projectmg.Exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
public class ProdutoService {

    private static final String MSG_PRODUTO = "Produto não encontrado";

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoSpec produtoSpec;

    public ProdutoDTO converterProdutoParaProdutoDTO(Produto produto){
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produto.getId());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setReferencia(produto.getReferencia());
        produtoDTO.setDataAtualizacao(produto.getDataAtualizacao());
        produtoDTO.setUsuarioUltimaAlteracao(produto.getUsuarioUltimaAlteracao());
        produtoDTO.setAcao(produto.getAcao());

        return produtoDTO;
    }

    public Produto converterProdutoDTOParaProduto(ProdutoDTO produtoDTO){
        Produto produto = new Produto();
        produto.setId(produtoDTO.getId());
        produto.setNome(produtoDTO.getNome());
        produto.setReferencia(produtoDTO.getReferencia());
        produto.setDataAtualizacao(produtoDTO.getDataAtualizacao());
        produto.setUsuarioUltimaAlteracao(produtoDTO.getUsuarioUltimaAlteracao());
        produto.setAcao(produtoDTO.getAcao());

        return produto;
    }

    public ProdutoDTO cadastrarProduto(ProdutoDTO produtoDTO){
        produtoSpec.verifyProdutoNome(produtoDTO.getNome());
        produtoSpec.verifyProdutoRef(produtoDTO.getReferencia());
        List<Produto> produtoNome = produtoRepository.findByNome(produtoDTO.getNome());
        List<Produto> produtoRef = produtoRepository.findByReferencia(produtoDTO.getReferencia());
        produtoSpec.verifyProdutoNomeExists(produtoNome);
        produtoSpec.verifyProdutoRefExists(produtoRef);
        Produto produto = converterProdutoDTOParaProduto(produtoDTO);
        produto.setAcao(produto.getId() == null ? TipoAcao.CRIADO : TipoAcao.ATUALIZADO);
        produto = produtoRepository.save(produto);

        return converterProdutoParaProdutoDTO(produto);
    }

    public ProdutoDTO atualizarProduto(ProdutoDTO produtoDTO){
        produtoSpec.verifyProdutoId(produtoDTO.getId());
        Produto produtoExistente = produtoRepository.findById(produtoDTO.getId())
                .orElseThrow(() -> new BusinessException(MSG_PRODUTO));
        produtoSpec.verifyProdutoNome(produtoDTO.getNome());
        produtoSpec.verifyProdutoRef(produtoDTO.getReferencia());
        produtoSpec.verifyProdutoNomeDup(produtoDTO.getNome(), produtoDTO.getId());
        produtoSpec.verifyProdutoRefDup(produtoDTO.getReferencia(), produtoDTO.getId());
        produtoExistente.setNome(produtoDTO.getNome());
        produtoExistente.setReferencia(produtoDTO.getReferencia());
        produtoExistente.setDataAtualizacao(LocalDate.now());
        produtoExistente.setUsuarioUltimaAlteracao(UsuarioAuditoria.getUsuarioLogado());
        produtoExistente.setAcao(TipoAcao.ATUALIZADO);
        produtoRepository.save(produtoExistente);

        return converterProdutoParaProdutoDTO(produtoExistente);
    }

    public void deletarProduto(Long id){
        Produto produto = produtoRepository.findById(id).orElseThrow();
        produto.setAcao(TipoAcao.DELETADO);
        produto.setDataAtualizacao(LocalDate.now());
        produto.setUsuarioUltimaAlteracao(UsuarioAuditoria.getUsuarioLogado());
        produtoRepository.save(produto);
        produtoRepository.deleteById(id);
    }

    public ProdutoDTO buscarProdutoPorId(Long id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MSG_PRODUTO));
        return converterProdutoParaProdutoDTO(produto);
    }

    public List<ProdutoDTO> buscarProdutoPorNome(String nome){
        List<Produto> produtos = produtoRepository.findByNome(nome);
        produtoSpec.verifyProduto(produtos);
        List<ProdutoDTO> dtos = new java.util.ArrayList<>();
        produtos.forEach(produto -> {
            dtos.add(converterProdutoParaProdutoDTO(produto));
        });

        return dtos;
    }

    public List<ProdutoDTO> buscarProdutoPorReferencia(String referencia){
        List<Produto> produtos = produtoRepository.findByReferencia(referencia);
        produtoSpec.verifyProduto(produtos);
        List<ProdutoDTO> dtos = new java.util.ArrayList<>();
        produtos.forEach(produto -> {
            dtos.add(converterProdutoParaProdutoDTO(produto));
        });

        return dtos;
    }

    public List<ProdutoDTO> buscarProdutoTodos(){
        List<Produto> produtos = produtoRepository.findAllAtivos();
        produtoSpec.verifyProduto(produtos);
        List<ProdutoDTO> dtos = new java.util.ArrayList<>();
        produtos.forEach(produto -> {
            dtos.add(converterProdutoParaProdutoDTO(produto));
        });

        return dtos;
    }
}
