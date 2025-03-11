package com.projectmg.Repositories;

import com.projectmg.Models.Costureira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CostureiraRepository extends JpaRepository <Costureira, Long> {

    Costureira findByBairro(String bairro);
}
