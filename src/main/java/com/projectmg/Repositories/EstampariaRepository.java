package com.projectmg.Repositories;

import com.projectmg.Models.Estamparia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstampariaRepository extends JpaRepository <Estamparia, Long> {

    Estamparia findByBairro(String bairro);

}
