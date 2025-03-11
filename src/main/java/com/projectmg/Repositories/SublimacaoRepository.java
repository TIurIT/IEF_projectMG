package com.projectmg.Repositories;

import com.projectmg.Models.Sublimacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SublimacaoRepository extends JpaRepository <Sublimacao, Long> {

    Sublimacao findByBairro(String bairro);
}
