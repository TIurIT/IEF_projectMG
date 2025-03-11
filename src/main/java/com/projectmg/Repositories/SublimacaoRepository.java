package com.projectmg.Repositories;

import com.projectmg.Models.Sublimacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SublimacaoRepository extends JpaRepository <Sublimacao, Long> {

    @Query("select s from Sublimacao s where s.bairro like %:bairro%")
    List<Sublimacao> findByBairro(String bairro);
}
