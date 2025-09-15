package com.projectmg.Repositories;

import com.projectmg.Models.HistoricoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoMaterialRepository extends JpaRepository<HistoricoMaterial, Long> {
    List<HistoricoMaterial> findByMaterialId(Long materialId);

    @Query("select h from HistoricoMaterial h left join fetch h.material order by h.dataAtualizacao desc")
    List<HistoricoMaterial> findAllHistorico();

}
