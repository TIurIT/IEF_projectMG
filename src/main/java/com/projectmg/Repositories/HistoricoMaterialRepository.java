package com.projectmg.Repositories;

import com.projectmg.Models.HistoricoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoMaterialRepository extends JpaRepository<HistoricoMaterial, Long> {
    List<HistoricoMaterial> findByMaterialId(Long materialId);
}
