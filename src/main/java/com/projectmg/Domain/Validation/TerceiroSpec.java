package com.projectmg.Domain.Validation;

import com.projectmg.Domain.Entity.Terceiro;
import com.projectmg.Repository.TerceiroRepository;
import com.projectmg.Exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class TerceiroSpec {

    @Autowired
    private TerceiroRepository terceiroRepository;

    private static final String MSG_TERCEIRO = "Terceiro não encontrado";

    public void verifyTerceiroNomeExists(List<Terceiro> terceiros) {
        if (terceiros.size() > 0) {
            throw new BusinessException(MSG_TERCEIRO);
        }
    }
    public void verifyTerceiroNome(String nome) {
        if (nome.isEmpty()) {
            throw new BusinessException(MSG_TERCEIRO);
        }
    }

    public void verifyTerceiroBairro(String bairro) {
        if (bairro.isEmpty()) {
            throw new BusinessException(MSG_TERCEIRO);
        }
        if (terceiroRepository.findByBairro(bairro).size() > 0) {
            throw new BusinessException(MSG_TERCEIRO);
        }
    }

    public void verifyTerceiroBairroExists(List<Terceiro> terceiros) {
        if (isNull(terceiros)) {
            throw new BusinessException(MSG_TERCEIRO);
        }
    }

    public void verifyTerceiroTelefone(String telefone) {
        if (telefone.isEmpty()) {
            throw new BusinessException(MSG_TERCEIRO);
        }
    }

//    public void verifyTerceiroServico(String servico) {
//        if (servico.isEmpty()) {
//            throw new BusinessException(MSG_TERCEIRO);
//        }
//
//        List<Terceiro> terceiros = terceiroRepository.findByServico(servico);
//        terceiros.forEach(terceiro -> {
//            if (terceiro.getServico().equals(servico)) {
//                throw new BusinessException(MSG_TERCEIRO);
//            }
//        });
//    }

    public void verifyTerceiroId(Long id) {
        if (isNull(id)) {
            throw new BusinessException(MSG_TERCEIRO);
        }
    }

    public void verifyTerceiro(List<Terceiro> terceiros) {
        if (terceiros.isEmpty()) {
            throw new BusinessException(MSG_TERCEIRO);
        }
    }
}