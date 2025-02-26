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

    public void cadastrarEstamparia(EstampariaDto estampariaDto) {
        Estamparia estamparia = converterEstampariaDtoParaEstamparia(estampariaDto);
        estampariaRepository.save(estamparia);
    }

    public void atualizarEstamparia(EstampariaDto estampariaDto) {
        Estamparia estamparia = converterEstampariaDtoParaEstamparia(estampariaDto);
        estampariaRepository.save(estamparia);
    }

    public void excluirEstamparia(EstampariaDto estampariaDto) {
        Estamparia estamparia = converterEstampariaDtoParaEstamparia(estampariaDto);
        estampariaRepository.delete(estamparia);
    }

    public Estamparia buscarEstamparia(Long di) {
        return estampariaRepository.findById(id);
    }
}
