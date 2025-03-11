package com.projectmg.Services;

import com.projectmg.DTO.SublimacaoDto;
import com.projectmg.Models.Sublimacao;
import com.projectmg.Repositories.SublimacaoRepository;
import com.projectmg.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SublimacaoService {

    private static final String MSG_SUBLIMACAO = "Sublimação não encontrada";

    @Autowired
    private SublimacaoRepository sublimacaoRepository;

    public Sublimacao converterSublimacaoDtoParaSublimacao(SublimacaoDto sublimacaoDto) {
        Sublimacao sublimacao = new Sublimacao();
        sublimacao.setId(sublimacaoDto.getId());
        sublimacao.setNome(sublimacaoDto.getNome());
        sublimacao.setBairro(sublimacaoDto.getBairro());
        sublimacao.setTelefone(sublimacaoDto.getTelefone());
        return sublimacao;
    }

    public SublimacaoDto converterSublimacaoParaSublimacaoDto(Sublimacao sublimacao) {
        SublimacaoDto sublimacaoDto = new SublimacaoDto();
        sublimacaoDto.setId(sublimacao.getId());
        sublimacaoDto.setNome(sublimacao.getNome());
        sublimacaoDto.setBairro(sublimacao.getBairro());
        sublimacaoDto.setTelefone(sublimacao.getTelefone());
        return sublimacaoDto;
    }

    public SublimacaoDto cadastrarSublimacao(SublimacaoDto sublimacaoDto){
        Sublimacao sublimacao = converterSublimacaoDtoParaSublimacao(sublimacaoDto);
        sublimacao = sublimacaoRepository.save(sublimacao);
        return converterSublimacaoParaSublimacaoDto(sublimacao);
    }

    public void deletarSublimacao(Long id){
        sublimacaoRepository.deleteById(id);
    }

    public SublimacaoDto atualizarSublimacao(SublimacaoDto sublimacaoDto){
        Sublimacao sublimacao = sublimacaoRepository.findById(sublimacaoDto.getId())
                .orElseThrow(() -> new BusinessException("MSG_SUBLIMACAO"));
        sublimacao = converterSublimacaoDtoParaSublimacao(sublimacaoDto);
        sublimacaoRepository.save(sublimacao);
        return  converterSublimacaoParaSublimacaoDto(sublimacao);
    }

    public SublimacaoDto buscarSublimacaoPorId(Long id){
        Sublimacao sublimacao = sublimacaoRepository.findById(id).orElseThrow(() -> new BusinessException(MSG_SUBLIMACAO));
        return converterSublimacaoParaSublimacaoDto(sublimacao);
    }

    public List<Sublimacao> buscarSublimacaoPorBairro(String bairro) {
        return sublimacaoRepository.findByBairro(bairro);
    }
}
