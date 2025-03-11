package com.projectmg.Services;

import com.projectmg.Dto.CostureiraDto;
import com.projectmg.Models.Costureira;
import com.projectmg.Repositories.CostureiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CostureiraService {

    private static final String MSG_COSTUREIRA = "Costureira não encontrada";

    @Autowired
    private CostureiraRepository costureiraRepository;

    public CostureiraDto converterCostureiraParaCostureiraDto(Costureira costureira){
        CostureiraDto costureiraDto = new CostureiraDto();
        costureiraDto.setId(costureira.getId());
        costureiraDto.setNome(costureira.getNome());
        costureiraDto.setBairro(costureira.getBairro());
        costureiraDto.setTelefone(costureira.getTelefone());
        return costureiraDto;
    }

    public Costureira converterCostureiraDtoParaCostureira(CostureiraDto costureiraDto){
        Costureira costureira = new Costureira();
        costureira.setId(costureiraDto.getId());
        costureira.setNome(costureiraDto.getNome());
        costureira.setBairro(costureiraDto.getBairro());
        costureira.setTelefone(costureiraDto.getTelefone());
        return costureira;
    }

    public CostureiraDto cadastrarCostureira(CostureiraDto costureiraDto){
        Costureira costureira = converterCostureiraDtoParaCostureira(costureiraDto);
        costureira = costureiraRepository.save(costureira);
        return converterCostureiraParaCostureiraDto(costureira);
    }

    public void deletarCostureira(Long id){
        costureiraRepository.deleteById(id);
    }

    public CostureiraDto atualizarCostureira(CostureiraDto costureiraDto){
        Costureira costureira = costureiraRepository.findById(costureiraDto.getId())
                .orElseThrow(() -> new RuntimeException("MSG_COSTUREIRA"));
        costureira = converterCostureiraDtoParaCostureira(costureiraDto);
        costureiraRepository.save(costureira);
        return  converterCostureiraParaCostureiraDto(costureira);
    }

    public CostureiraDto buscarCostureiraPorId(Long id){
        Costureira costureira = costureiraRepository.findById(id).orElseThrow(() -> new RuntimeException(MSG_COSTUREIRA));
        return converterCostureiraParaCostureiraDto(costureira);
    }

    public List<Costureira> buscarCostureiraPorBairro(String bairro){
        return costureiraRepository.findByBairro(bairro);
    }
}
