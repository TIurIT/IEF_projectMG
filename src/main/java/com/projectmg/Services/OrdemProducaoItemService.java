package com.projectmg.Services;

import com.projectmg.Dto.OrdemProducaoItemDTO;
import com.projectmg.Models.OrdemProducaoItem;
import com.projectmg.Repositories.OrdemProducaoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.projectmg.Specs.OrdemSpec.MSG_OPERACAO;

@Service
public class OrdemProducaoItemService {


    @Autowired
    private OrdemProducaoItemRepository ordemProducaoItemRepository;

    public OrdemProducaoItemDTO converterOrdemProducaoItemParaOrdemProducaoItemDTO(OrdemProducaoItem ordemProducaoItem){
        OrdemProducaoItemDTO ordemProducaoItemDTO = new OrdemProducaoItemDTO();
        ordemProducaoItemDTO.setId(ordemProducaoItem.getId());
        ordemProducaoItemDTO.setGradeDescription(ordemProducaoItem.getGradeDescription());
        ordemProducaoItemDTO.setQuantidadeTotal(ordemProducaoItem.getQuantidadeTotal());
        ordemProducaoItemDTO.setProduto(ordemProducaoItem.getProduto());
        ordemProducaoItemDTO.setMaterial(ordemProducaoItem.getMaterial());
        return ordemProducaoItemDTO;
    }

    public OrdemProducaoItem converterOrdemProducaoItemDTOParaOrdemProducaoItem(OrdemProducaoItemDTO ordemProducaoItemDTO){
        OrdemProducaoItem ordemProducaoItem = new OrdemProducaoItem();
        ordemProducaoItem.setId(ordemProducaoItemDTO.getId());
        ordemProducaoItem.setGradeDescription(ordemProducaoItemDTO.getGradeDescription());
        ordemProducaoItem.setQuantidadeTotal(ordemProducaoItemDTO.getQuantidadeTotal());
        ordemProducaoItem.setProduto(ordemProducaoItemDTO.getProduto());
        ordemProducaoItem.setMaterial(ordemProducaoItemDTO.getMaterial());
        return ordemProducaoItem;
    }

    public OrdemProducaoItemDTO cadastrarOrdemProducaoItem(OrdemProducaoItemDTO ordemProducaoItemDTO){
        OrdemProducaoItem ordemProducaoItem = converterOrdemProducaoItemDTOParaOrdemProducaoItem(ordemProducaoItemDTO);
        ordemProducaoItem = ordemProducaoItemRepository.save(ordemProducaoItem);
        return converterOrdemProducaoItemParaOrdemProducaoItemDTO(ordemProducaoItem);
    }

    public OrdemProducaoItemDTO atualizarOrdemProducaoItem(OrdemProducaoItemDTO ordemProducaoItemDTO){
        OrdemProducaoItem ordemProducaoItem = converterOrdemProducaoItemDTOParaOrdemProducaoItem(ordemProducaoItemDTO);
        ordemProducaoItem = ordemProducaoItemRepository.save(ordemProducaoItem);
        return converterOrdemProducaoItemParaOrdemProducaoItemDTO(ordemProducaoItem);
    }

    public void deletarOrdemProducaoItem(Long id){
        ordemProducaoItemRepository.deleteById(id);
    }

    public OrdemProducaoItemDTO buscarOrdemProducaoItemPorId(Long id){
        OrdemProducaoItem ordemProducaoItem = ordemProducaoItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_OPERACAO));
        return converterOrdemProducaoItemParaOrdemProducaoItemDTO(ordemProducaoItem);
    }

}
