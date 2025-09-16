package com.projectmg.Services;

import com.projectmg.Dto.HistoricoMaterialDTO;
import com.projectmg.Models.HistoricoMaterial;
import com.projectmg.Repositories.HistoricoMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricoMaterialService {

    @Autowired
    private HistoricoMaterialRepository historicoRepository;

    public List<HistoricoMaterialDTO> listarHistoricoPorMaterial(Long materialId) {
        List<HistoricoMaterial> historico = historicoRepository.findByMaterialIdFetch(materialId);
        return historico.stream()
                .map(HistoricoMaterialDTO::fromEntity) // 🔹 usa fromEntity
                .collect(Collectors.toList());
    }

    // 🔹 Histórico geral
    public List<HistoricoMaterialDTO> listarHistoricoGeral() {
        return historicoRepository.findAllOrdered()
                .stream()
                .map(HistoricoMaterialDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
