package com.projectmg.Services;

import com.projectmg.Enum.StatusDespache;
import com.projectmg.Models.DespacheTerceiro;
import com.projectmg.Models.Material;
import com.projectmg.Models.Terceiro;
import com.projectmg.Repositories.DespacheTerceiroRepository;
import com.projectmg.Repositories.MaterialRepository;
import com.projectmg.Repositories.TerceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespacheTerceiroService {

    @Autowired
    private final DespacheTerceiroRepository despacheRepository;
    @Autowired
    private final TerceiroRepository terceiroRepository;
    @Autowired
    private final MaterialRepository materialRepository;

    public DespacheTerceiroService(
            DespacheTerceiroRepository despacheRepository,
            TerceiroRepository terceiroRepository,
            MaterialRepository materialRepository
    ) {
        this.despacheRepository = despacheRepository;
        this.terceiroRepository = terceiroRepository;
        this.materialRepository = materialRepository;
    }

    // Enviar material para o terceiro
    public DespacheTerceiro despachar(Long terceiroId, Long materialId, Double quantidade) {
        Terceiro terceiro = terceiroRepository.findById(terceiroId)
                .orElseThrow(() -> new RuntimeException("Terceiro não encontrado"));

        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));

        if (!material.isAtivo()) {
            throw new RuntimeException("Material inativo não pode ser enviado.");
        }

        if (material.getQuantidade() < quantidade) {
            throw new RuntimeException("Quantidade maior do que disponível no estoque.");
        }

        DespacheTerceiro envio = new DespacheTerceiro();
        envio.setTerceiro(terceiro);
        envio.setMaterial(material);
        envio.setQuantidadeEnviada(quantidade);
        envio.setQuantidadeRetornada(0.0);

        return despacheRepository.save(envio);
    }

    // Registrar retorno
    public DespacheTerceiro registrarRetorno(Long id, Double quantidadeRetorno) {
        DespacheTerceiro despache = despacheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        despache.setQuantidadeRetornada(quantidadeRetorno);
        despache.setDataRetorno(java.time.LocalDateTime.now());

        if (quantidadeRetorno.equals(despache.getQuantidadeEnviada())) {
            despache.setStatusDespache(StatusDespache.RETORNADO);
        } else if (quantidadeRetorno > 0) {
            despache.setStatusDespache(StatusDespache.PARCIAL);
        }

        return despacheRepository.save(despache);
    }

    public List<DespacheTerceiro> listarTodos() {
        return despacheRepository.findAll();
    }

    public List<DespacheTerceiro> listarPorTerceiro(Long terceiroId) {
        return despacheRepository.findByTerceiroId(terceiroId);
    }

}
