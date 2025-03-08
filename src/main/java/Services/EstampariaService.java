package Services;

import DTO.EstampariaDto;
import Models.Estamparia;
import Repositories.EstampariaRepository;
import exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EstampariaService {
    private static final String MSG_ESTAMPARIA = "Estamparia não encontrada";
    @Autowired
    private EstampariaRepository estampariaRepository;

    public Estamparia converterEstampariaDtoParaEstamparia(EstampariaDto estampariaDto) {
        Estamparia estamparia = new Estamparia();
        estamparia.setId(estampariaDto.getId());
        estamparia.setNome(estampariaDto.getNome());
        estamparia.setBairro(estampariaDto.getBairro());
        estamparia.setTelefone(estampariaDto.getTelefone());
        return estamparia;
    }

    public EstampariaDto converterEstampariaParaEstampariaDto(Estamparia estamparia) {
        EstampariaDto estampariaDto = new EstampariaDto();
        estampariaDto.setId(estamparia.getId());
        estampariaDto.setNome(estamparia.getNome());
        estampariaDto.setBairro(estamparia.getBairro());
        estampariaDto.setTelefone(estamparia.getTelefone());
        return estampariaDto;
    }

    public EstampariaDto cadastrarEstamparia(EstampariaDto estampariaDto){
        Estamparia estamparia = converterEstampariaDtoParaEstamparia(estampariaDto);
        estamparia = estampariaRepository.save(estamparia);
        return converterEstampariaParaEstampariaDto(estamparia);
    }

    public void deletarEstamparia(UUID id){
        estampariaRepository.deleteById(id);
    }

    public EstampariaDto atualizarEstamparia(EstampariaDto estampariaDto){
        Estamparia estamparia = estampariaRepository.findById(estampariaDto.getId())
                .orElseThrow(() -> new BusinessException(MSG_ESTAMPARIA));
        estamparia = converterEstampariaDtoParaEstamparia(estampariaDto);
        estampariaRepository.save(estamparia);
        return  converterEstampariaParaEstampariaDto(estamparia);
    }

    public EstampariaDto buscarEstampariaPorId(UUID id){
        Estamparia estamparia = estampariaRepository.findById(id).orElseThrow(() -> new BusinessException(MSG_ESTAMPARIA));
        return converterEstampariaParaEstampariaDto(estamparia);
    }

    public EstampariaDto buscarEstampariaPorBairro(String bairro){
        Estamparia estamparia = estampariaRepository.findByBairro(bairro);
        return converterEstampariaParaEstampariaDto(estamparia);
    }
}
