package com.projectmg.Services;

import com.projectmg.Dto.OrdemProducaoTerceiroDTO;
import com.projectmg.Models.OrdemProducaoTerceiro;
import com.projectmg.Repositories.OrdemProducaoTerceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.projectmg.Specs.OrdemSpec.MSG_OPERACAO;


@Service
public class OrdemProducaoTerceiroService {

    @Autowired
    private OrdemProducaoTerceiroRepository ordemProducaoTerceiroRepository;

    public OrdemProducaoTerceiroDTO converterOPTemOPTDTO(OrdemProducaoTerceiro ordemProducaoTerceiro) {
        OrdemProducaoTerceiroDTO ordemProducaoTerceiroDTO = new OrdemProducaoTerceiroDTO();
        ordemProducaoTerceiroDTO.setId(ordemProducaoTerceiro.getId());
        ordemProducaoTerceiroDTO.setTerceiro(ordemProducaoTerceiro.getTerceiro());
        ordemProducaoTerceiroDTO.setOrdens(ordemProducaoTerceiro.getOrdens());
        ordemProducaoTerceiroDTO.setStatus(ordemProducaoTerceiro.getStatus());
        return ordemProducaoTerceiroDTO;
    }

    public OrdemProducaoTerceiro converterOPTDTOemOPT(OrdemProducaoTerceiroDTO ordemProducaoTerceiroDTO) {
        OrdemProducaoTerceiro ordemProducaoTerceiro = new OrdemProducaoTerceiro();
        ordemProducaoTerceiro.setId(ordemProducaoTerceiroDTO.getId());
        ordemProducaoTerceiro.setTerceiro(ordemProducaoTerceiroDTO.getTerceiro());
        ordemProducaoTerceiro.setOrdens(ordemProducaoTerceiroDTO.getOrdens());
        ordemProducaoTerceiro.setStatus(ordemProducaoTerceiroDTO.getStatus());
        return ordemProducaoTerceiro;
    }

    public OrdemProducaoTerceiroDTO cadastrarOPT(OrdemProducaoTerceiroDTO ordemProducaoTerceiroDTO) {
        OrdemProducaoTerceiro ordemProducaoTerceiro = converterOPTDTOemOPT(ordemProducaoTerceiroDTO);
        ordemProducaoTerceiro = ordemProducaoTerceiroRepository.save(ordemProducaoTerceiro);
        return converterOPTemOPTDTO(ordemProducaoTerceiro);
    }

    public OrdemProducaoTerceiroDTO atualizarOPT(OrdemProducaoTerceiroDTO ordemProducaoTerceiroDTO) {
        OrdemProducaoTerceiro ordemProducaoTerceiro = converterOPTDTOemOPT(ordemProducaoTerceiroDTO);
        ordemProducaoTerceiro = ordemProducaoTerceiroRepository.save(ordemProducaoTerceiro);
        return converterOPTemOPTDTO(ordemProducaoTerceiro);
    }

    public void deletarOPT(Long id){
        ordemProducaoTerceiroRepository.deleteById(id);
    }

    public OrdemProducaoTerceiroDTO buscarOPT(Long id){
        OrdemProducaoTerceiro ordemProducaoTerceiro = ordemProducaoTerceiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_OPERACAO));
        return converterOPTemOPTDTO(ordemProducaoTerceiro);
    }

    public List<OrdemProducaoTerceiroDTO> buscarOrdemPorTerceiro(String nome){
        List<OrdemProducaoTerceiro> terceiros = OrdemProducaoTerceiroRepository.findByTerceiro(nome);
        List<OrdemProducaoTerceiroDTO> dtos = new ArrayList<>();
            terceiros.forEach(ordemProducaoTerceiro -> {
                dtos.add(converterOPTemOPTDTO(ordemProducaoTerceiro));
            });

            return dtos;
    }
}
