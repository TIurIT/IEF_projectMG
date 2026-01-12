package com.projectmg.Repository;

import com.projectmg.Domain.Entity.DespacheTerceiro;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespacheTerceiroRepository extends JpaRepository<DespacheTerceiro, Long> {

    List<DespacheTerceiro> findByTerceiroId(Long id);

}
