package com.projectmg.Service;

import com.projectmg.Domain.Dto.HistoricoMaterialDTO;
import com.projectmg.Domain.Entity.HistoricoMaterial;
import com.projectmg.Repository.HistoricoMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
