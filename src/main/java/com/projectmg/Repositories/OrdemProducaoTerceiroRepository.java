package com.projectmg.Repositories;

import com.projectmg.Models.OrdemProducao;
import com.projectmg.Models.OrdemProducaoTerceiro;
import com.projectmg.Models.Terceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemProducaoTerceiroRepository extends JpaRepository<OrdemProducaoTerceiro, Long> {

    @Query("SELECT o FROM OrdemProducaoTerceiro o WHERE o.terceiro.nome LIKE %:nome%")
    List<OrdemProducaoTerceiro> findByTerceiro(String nome);
}
