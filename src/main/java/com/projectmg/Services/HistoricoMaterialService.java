package com.projectmg.Services;

import com.projectmg.Dto.HistoricoMaterialDTO;
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

    // HistoricoMaterialService.java
    public List<HistoricoMaterialDTO> listarUltimosHistoricos() {
        return historicoRepository.findAllHistorico()
                .stream()
                .map(HistoricoMaterialDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<HistoricoMaterialDTO> listarHistorico(Long materialId) {
        return historicoRepository.findByMaterialId(materialId)
                .stream()
                .map(HistoricoMaterialDTO::fromEntity) // já inclui nome e tratamento de removido
                .sorted((h1, h2) -> {
                    LocalDateTime d1 = h1.dataAtualizacao() != null ? h1.dataAtualizacao() : h1.dataCriacao();
                    LocalDateTime d2 = h2.dataAtualizacao() != null ? h2.dataAtualizacao() : h2.dataCriacao();
                    return d2.compareTo(d1); // ordem decrescente
                })
                .collect(Collectors.toList());
    }
   }
