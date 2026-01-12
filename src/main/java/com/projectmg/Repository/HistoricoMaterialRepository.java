package com.projectmg.Repository;

import com.projectmg.Domain.Entity.HistoricoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoMaterialRepository extends JpaRepository<HistoricoMaterial, Long> {

    @Query("SELECT h FROM HistoricoMaterial h LEFT JOIN FETCH h.material WHERE h.material.id = :materialId ORDER BY h.dataAtualizacao DESC")
    List<HistoricoMaterial> findByMaterialIdFetch(@Param("materialId") Long materialId);


    // Histórico geral de todos os materiais (já com JOIN para evitar Lazy)
    @Query("select h from HistoricoMaterial h left join fetch h.material order by h.dataAtualizacao desc")
    List<HistoricoMaterial> findAllOrdered();
}
