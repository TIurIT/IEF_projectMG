package Services;

import DTO.SublimacaoDto;
import Models.Sublimacao;
import Repositories.SublimacaoRepository;
import exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public void deletarSublimacao(UUID id){
        sublimacaoRepository.deleteById(id);
    }

    public SublimacaoDto atualizarSublimacao(SublimacaoDto sublimacaoDto){
        Sublimacao sublimacao = sublimacaoRepository.findById(sublimacaoDto.getId())
                .orElseThrow(() -> new BusinessException("MSG_SUBLIMACAO"));
        sublimacao = converterSublimacaoDtoParaSublimacao(sublimacaoDto);
        sublimacaoRepository.save(sublimacao);
        return  converterSublimacaoParaSublimacaoDto(sublimacao);
    }

    public SublimacaoDto buscarSublimacaoPorId(UUID id){
        Sublimacao sublimacao = sublimacaoRepository.findById(id).orElseThrow(() -> new BusinessException(MSG_SUBLIMACAO));
        return converterSublimacaoParaSublimacaoDto(sublimacao);
    }

    public SublimacaoDto buscarSublimacaoPorBairro(String bairro){
        Sublimacao sublimacao = sublimacaoRepository.findByBairro(bairro);
        return converterSublimacaoParaSublimacaoDto(sublimacao);
    }
}
