package com.projectmg.Repositories;

import com.projectmg.Models.OrdemProducao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemProducaoRepository extends JpaRepository<OrdemProducao, Long> {

   @Query("SELECT o FROM OrdemProducao o WHERE o.clienteId = :id")
   List<OrdemProducao> findByClienteId(Long id);
}
