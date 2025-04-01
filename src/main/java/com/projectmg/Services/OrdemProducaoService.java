package com.projectmg.Services;

import com.projectmg.Dto.OrdemProducaoDTO;
import com.projectmg.Models.OrdemProducao;
import com.projectmg.Repositories.OrdemProducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemProducaoService {

    private static final String MSG_OPERACAO = "Operação nao encontrada";

    @Autowired
    private OrdemProducaoRepository ordemProducaoRepository;

    public OrdemProducaoDTO converterOrdemProducaoParaOrdemProducaoDTO(OrdemProducao ordemProducao){
        OrdemProducaoDTO ordemProducaoDTO = new OrdemProducaoDTO();
        ordemProducaoDTO.setId(ordemProducao.getId());
        ordemProducaoDTO.setClienteId(ordemProducao.getClienteId());
        ordemProducaoDTO.setGradeDescription(ordemProducao.getGradeDescription());
        ordemProducaoDTO.setQuantidade_total(ordemProducao.getQuantidade_total());
        ordemProducaoDTO.setProduto(ordemProducao.getProduto());
        ordemProducaoDTO.setMaterial(ordemProducao.getMaterial());
        return ordemProducaoDTO;
    }

    public OrdemProducao converterOrdemProducaoDTOParaOrdemProducao(OrdemProducaoDTO ordemProducaoDTO){
        OrdemProducao ordemProducao = new OrdemProducao();
        ordemProducao.setId(ordemProducaoDTO.getId());
        ordemProducao.setClienteId(ordemProducaoDTO.getClienteId());
        ordemProducao.setGradeDescription(ordemProducaoDTO.getGradeDescription());
        ordemProducao.setQuantidade_total(ordemProducaoDTO.getQuantidade_total());
        ordemProducao.setProduto(ordemProducaoDTO.getProduto());
        ordemProducao.setMaterial(ordemProducaoDTO.getMaterial());
        return ordemProducao;
    }

    public OrdemProducaoDTO cadastrarOrdemProducao(OrdemProducaoDTO ordemProducaoDTO){
        OrdemProducao ordemProducao = converterOrdemProducaoDTOParaOrdemProducao(ordemProducaoDTO);
        ordemProducao = ordemProducaoRepository.save(ordemProducao);
        return converterOrdemProducaoParaOrdemProducaoDTO(ordemProducao);
    }

    public OrdemProducaoDTO atualizarOrdemProducao(OrdemProducaoDTO ordemProducaoDTO){
        OrdemProducao ordemProducao = converterOrdemProducaoDTOParaOrdemProducao(ordemProducaoDTO);
        ordemProducao = ordemProducaoRepository.save(ordemProducao);
        return converterOrdemProducaoParaOrdemProducaoDTO(ordemProducao);
    }

    public void deletarOrdemProducao(Long id){
        ordemProducaoRepository.deleteById(id);
    }

    public OrdemProducaoDTO buscarOrdemProducaoPorId(Long id){
        OrdemProducao ordemProducao = ordemProducaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MSG_OPERACAO"));
        return converterOrdemProducaoParaOrdemProducaoDTO(ordemProducao);
    }

    public List<OrdemProducaoDTO> buscarOrdemProducaoPorCliente(Long id){
        List<OrdemProducao> ordemProducaos = ordemProducaoRepository.findByClienteId(id);
        List<OrdemProducaoDTO> dtos = new java.util.ArrayList<>();
        ordemProducaos.forEach(ordemProducao -> {
            dtos.add(converterOrdemProducaoParaOrdemProducaoDTO(ordemProducao));
        });
        return dtos;
    }
}
