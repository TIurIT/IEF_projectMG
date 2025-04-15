package com.projectmg.Repositories;

import com.projectmg.Models.OrdemProducaoTerceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OrdemProducaoTerceiroRepository extends JpaRepository<OrdemProducaoTerceiro, Long> {

    @Query("SELECT o FROM OrdemProducaoTerceiro o WHERE o.terceiro.nome = :nome")
    static List<OrdemProducaoTerceiro> findByTerceiro(String nome) {
        return new ArrayList<>();
    }
}
