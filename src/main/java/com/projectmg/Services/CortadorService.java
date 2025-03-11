package com.projectmg.Services;

import com.projectmg.Dto.CortadorDto;
import com.projectmg.Models.Cortador;
import com.projectmg.Repositories.CortadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CortadorService {

    private static final String MSG_CORTADOR = "Cortador não encontrado";

    @Autowired
    private CortadorRepository cortadorRepository;

    public CortadorDto converterCortadorParaCortadorDto(Cortador cortador){
        CortadorDto cortadorDto = new CortadorDto();
        cortadorDto.setId(cortador.getId());
        cortadorDto.setNome(cortador.getNome());
        cortadorDto.setBairro(cortador.getBairro());
        cortadorDto.setTelefone(cortador.getTelefone());
        return cortadorDto;
    }

    public Cortador converterCortadorDtoParaCortador(CortadorDto cortadorDto){
        Cortador cortador = new Cortador();
        cortador.setId(cortadorDto.getId());
        cortador.setNome(cortadorDto.getNome());
        cortador.setBairro(cortadorDto.getBairro());
        cortador.setTelefone(cortadorDto.getTelefone());
        return cortador;
    }

    public CortadorDto cadastrarCortador(CortadorDto cortadorDto){
        Cortador cortador = converterCortadorDtoParaCortador(cortadorDto);
        cortador = cortadorRepository.save(cortador);
        return converterCortadorParaCortadorDto(cortador);
    }

    public void deletarCortador(Long id){
        cortadorRepository.deleteById(id);
    }

    public CortadorDto atualizarCortador(CortadorDto cortadorDto){
        Cortador cortador = cortadorRepository.findById(cortadorDto.getId())
                .orElseThrow(() -> new RuntimeException("MSG_CORTADOR"));
        cortador = converterCortadorDtoParaCortador(cortadorDto);
        cortadorRepository.save(cortador);
        return  converterCortadorParaCortadorDto(cortador);
    }

    public CortadorDto buscarCortadorPorId(Long id){
        Cortador cortador = cortadorRepository.findById(id).orElseThrow(() -> new RuntimeException(MSG_CORTADOR));
        return converterCortadorParaCortadorDto(cortador);
    }

    public List<Cortador> buscarCortadorPorBairro(String bairro){
        return cortadorRepository.findByBairro(bairro);
    }
}
