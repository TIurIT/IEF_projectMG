package com.projectmg.Services;

import com.projectmg.Dto.TerceirosDTO;
import com.projectmg.Models.Terceiros;
import com.projectmg.Repositories.TerceirosRepository;
import com.projectmg.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerceirosService {

    private static final String MSG_TERCEIRO = "Terceiro não encontrado";
    @Autowired
    private TerceirosRepository terceirosRepository;

    public Terceiros converterTerceirosDTOParaTerceiros(TerceirosDTO TerceirosDTO){
        Terceiros terceiros = new Terceiros();
        terceiros.setId(TerceirosDTO.getId());
        terceiros.setNome(TerceirosDTO.getNome());
        terceiros.setBairro(TerceirosDTO.getBairro());
        terceiros.setTelefone(TerceirosDTO.getTelefone());
        terceiros.setServico(TerceirosDTO.getServico());
        return terceiros;
    }

    public TerceirosDTO converterTerceirosParaTerceirosDTO(Terceiros terceiros){
        TerceirosDTO TerceirosDTO = new TerceirosDTO();
        TerceirosDTO.setId(terceiros.getId());
        TerceirosDTO.setNome(terceiros.getNome());
        TerceirosDTO.setBairro(terceiros.getBairro());
        TerceirosDTO.setTelefone(terceiros.getTelefone());
        TerceirosDTO.setServico(terceiros.getServico());
        return TerceirosDTO;
    }

    public TerceirosDTO cadastrarTerceiros(TerceirosDTO TerceirosDTO){
        Terceiros terceiros = converterTerceirosDTOParaTerceiros(TerceirosDTO);
        terceiros = terceirosRepository.save(terceiros);
        return converterTerceirosParaTerceirosDTO(terceiros);
    }

    public TerceirosDTO atualizarTerceiros(TerceirosDTO TerceirosDTO){
        Terceiros terceiros = terceirosRepository.findById(TerceirosDTO.getId())
                .orElseThrow(() -> new BusinessException(MSG_TERCEIRO));
        terceiros = converterTerceirosDTOParaTerceiros(TerceirosDTO);
        terceirosRepository.save(terceiros);
        return converterTerceirosParaTerceirosDTO(terceiros);
    }

    public void deletarTerceiros(Long id){
        terceirosRepository.deleteById(id);
    }

    public TerceirosDTO buscarTerceirosPorId(Long id){
        Terceiros terceiros = terceirosRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MSG_TERCEIRO));
        return converterTerceirosParaTerceirosDTO(terceiros);
    }

    public TerceirosDTO buscarTerceirosPorNome(String nome){
        Terceiros terceiros = terceirosRepository.findByNome(nome)
                .orElseThrow(() -> new BusinessException(MSG_TERCEIRO));
        return converterTerceirosParaTerceirosDTO(terceiros);
    }

    public TerceirosDTO buscarTerceirosPorBairro(String bairro){
        Terceiros terceiros = terceirosRepository.findByBairro(bairro)
                .orElseThrow(() -> new BusinessException(MSG_TERCEIRO));
        return converterTerceirosParaTerceirosDTO(terceiros);
    }
}
