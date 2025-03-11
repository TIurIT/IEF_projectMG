package com.projectmg.Repositories;

import com.projectmg.Models.Costureira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CostureiraRepository extends JpaRepository <Costureira, Long> {

    @Query("select c from Costureira c where c.bairro like %:bairro%")
    List<Costureira> findByBairro(String bairro);
}
