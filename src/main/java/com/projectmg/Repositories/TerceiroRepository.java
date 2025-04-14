package com.projectmg.Repositories;

import com.projectmg.Models.Terceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerceiroRepository extends JpaRepository<Terceiro, Long> {

    @Query("SELECT t FROM Terceiro t WHERE t.nome LIKE %:nome%")
    List<Terceiro> findByNome(String nome);

    @Query("SELECT t FROM Terceiro t WHERE t.bairro LIKE %:bairro%")
    List<Terceiro> findByBairro(String bairro);

    @Query("SELECT t FROM Terceiro t WHERE t.servico LIKE %:servico%")
    List<Terceiro> findByServico(String servico);
}
