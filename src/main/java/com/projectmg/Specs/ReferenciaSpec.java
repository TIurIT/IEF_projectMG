package com.projectmg.Specs;

import com.projectmg.Models.Referencia;
import com.projectmg.Exceptions.BusinessException;
import com.projectmg.Repositories.ReferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class ReferenciaSpec {

    @Autowired
    private ReferenciaRepository referenciaRepository;

    private static final String MSG_PRODUTO = "Produto não encontrado.";
    private static final String MSG_PRODUTO_REF = "Referência ja cadastrada.";
    private static final String MSG_PRODUTO_NOME = "Nome já cadastrado.";
    private static final String MSG_PRODUTO_NullREF = "Referência não pode ser vazia.";
    private static final String MSG_PRODUTO_NullNOME = "Nome não pode ser vazio.";

    public void verifyProdutoNomeExists(List<Referencia> referencias) {
        if (referencias.size() > 0) {
            throw new BusinessException(MSG_PRODUTO_NOME);
        }
    }

    public void verifyProduto(List<Referencia> referencias) {
        if (referencias.size() == 0) {
            throw new BusinessException(MSG_PRODUTO);
        }
    }

    public void verifyProdutoRefExists(List<Referencia> referencias) {
        if (referencias.size() > 0) {
            throw new BusinessException(MSG_PRODUTO_REF);
        }
    }

    public void verifyProdutoRef(String referencia) {
        if (referencia.isEmpty()) {
            throw new BusinessException(MSG_PRODUTO_NullREF);
        }
    }

    public void verifyProdutoNome(String nome) {
        if (nome.isEmpty()) {
            throw new BusinessException(MSG_PRODUTO_NullNOME);
        }
    }

    public void verifyProdutoId(Long id) {
        if (isNull(id)) {
            throw new BusinessException(MSG_PRODUTO);
        }
    }

    public void verifyProdutoNomeDup(String nome, Long id) {
        if (referenciaRepository.existsByNomeAndIdNot(nome, id)){
            throw new BusinessException(MSG_PRODUTO_NOME);
        }
    }

    public void verifyProdutoRefDup(String ref, Long id) {
        if (referenciaRepository.existsByReferenciaAndIdNot(ref, id)){
            throw new BusinessException(MSG_PRODUTO_NOME);
        }
    }
}
