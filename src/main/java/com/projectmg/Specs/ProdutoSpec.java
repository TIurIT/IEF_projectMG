package com.projectmg.Specs;

import com.projectmg.Models.Produto;
import com.projectmg.Services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoSpec {

    @Autowired
    private ProdutoService produtoService;

    private static final String MSG_PRODUTO = "Produto não encontrado";
    private static final String MSG_PRODUTO_REF = "Referência ja cadastrada";
    private static final String MSG_PRODUTO_NOME = "Nome já cadastrado";
    private static final String MSG_PRODUTO_NullREF = "Referência não pode ser vazia";
    private static final String MSG_PRODUTO_NullNOME = "Nome não pode ser vazio";
    private static final String MSG_PRODUTO_NullID = "Id não pode ser vazio";

    public void verifyProdutoNomeExists(List<Produto> produtos) {
        if (produtos.size() > 0) {
            throw new RuntimeException(MSG_PRODUTO_NOME);
        }
    }

    public void verifyProdutoAllNome(List<Produto> produtos) {
        if (produtos.size() == 0) {
            throw new RuntimeException(MSG_PRODUTO_NOME);
        }
    }

    public void verifyProdutoNome(String nome) {
        if (nome.isEmpty()) {
            throw new RuntimeException(MSG_PRODUTO_NullNOME);
        }
    }

    public void verifyProdutoRefExists(List<Produto> produtos) {
        if (produtos.size() > 0) {
            throw new RuntimeException(MSG_PRODUTO_REF);
        }
    }

    public void verifyProdutoRef(String referencia) {
        if (referencia.isEmpty()) {
            throw new RuntimeException(MSG_PRODUTO_NullREF);
        }
    }

    public void verifyProdutoAllRef(List<Produto> produtos) {
        if (produtos.size() == 0) {
            throw new RuntimeException(MSG_PRODUTO_NOME);
        }
    }
}
