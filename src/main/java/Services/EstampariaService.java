package Services;

import DTO.EstampariaDto;
import Models.Estamparia;
import Repositories.EstampariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstampariaService {

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
}
