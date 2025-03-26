package com.projectmg.Repositories;

import com.projectmg.Models.OrdemProducao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemProducaoRepository extends JpaRepository<OrdemProducao, Long> {
}
