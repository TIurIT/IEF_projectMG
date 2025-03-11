package com.projectmg.Repositories;

import com.projectmg.Models.Cortador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CortadorRepository extends JpaRepository<Cortador, Long> {

    @Query("select c from Cortador c where c.bairro like %:bairro%")
    List<Cortador> findByBairro(String bairro);
}
