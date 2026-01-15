package com.projectmg.Service.Material;

import com.projectmg.Domain.Dto.Material.HistoricoMaterialDTO;
import com.projectmg.Domain.Entity.Material.HistoricoMaterial;
import com.projectmg.Repository.Material.HistoricoMaterialRepository;
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
