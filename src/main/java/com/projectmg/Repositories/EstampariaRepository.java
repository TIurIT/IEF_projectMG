package com.projectmg.Repositories;

import com.projectmg.Models.Estamparia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EstampariaRepository extends JpaRepository <Estamparia, Long> {

    @Query("select e from Estamparia e where e.bairro like %:bairro%")
    List<Estamparia> findByBairro(String bairro);

}
