package com.projectmg.Domain.Validation;

import com.projectmg.Domain.Entity.Referencia;
import com.projectmg.Exception.BusinessException;
import com.projectmg.Repository.ReferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class ReferenciaSpec {

    @Autowired
    private ReferenciaRepository referenciaRepository;

    private static final String MSG_REFERENCIA = "Referencia não encontrado.";
    private static final String MSG_REFERENCIA_REF = "Referência ja cadastrada.";
    private static final String MSG_REFERENCIA_NOME = "Nome já cadastrado.";
    private static final String MSG_REFERENCIA_NullREF = "Referência não pode ser vazia.";
    private static final String MSG_REFERENCIA_NullNOME = "Nome não pode ser vazio.";

    public void verifyReferenciaNomeExists(List<Referencia> referencias) {
        if (referencias.size() > 0) {
            throw new BusinessException(MSG_REFERENCIA_NOME);
        }
    }

    public void verifyReferencia(List<Referencia> referencias) {
        if (referencias.size() == 0) {
            throw new BusinessException(MSG_REFERENCIA);
        }
    }

    public void verifyReferenciaRefExists(List<Referencia> referencias) {
        if (referencias.size() > 0) {
            throw new BusinessException(MSG_REFERENCIA_REF);
        }
    }

    public void verifyReferenciaRef(String referencia) {
        if (referencia.isEmpty()) {
            throw new BusinessException(MSG_REFERENCIA_NullREF);
        }
    }

    public void verifyReferenciaNome(String nome) {
        if (nome.isEmpty()) {
            throw new BusinessException(MSG_REFERENCIA_NullNOME);
        }
    }

    public void verifyReferenciaId(Long id) {
        if (isNull(id)) {
            throw new BusinessException(MSG_REFERENCIA);
        }
    }

    public void verifyReferenciaNomeDup(String nome, Long id) {
        if (referenciaRepository.existsByNomeAndIdNot(nome, id)){
            throw new BusinessException(MSG_REFERENCIA_NOME);
        }
    }

    public void verifyReferenciaRefDup(String ref, Long id) {
        if (referenciaRepository.existsByReferenciaAndIdNot(ref, id)){
            throw new BusinessException(MSG_REFERENCIA_NOME);
        }
    }
}
