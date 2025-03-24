package com.projectmg.Repositories;

import com.projectmg.Models.Terceiros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TerceirosRepository extends JpaRepository<Terceiros, Long> {

    Optional<Terceiros> findByNome(String nome);
    Optional<Terceiros> findByBairro(String bairro);
}
