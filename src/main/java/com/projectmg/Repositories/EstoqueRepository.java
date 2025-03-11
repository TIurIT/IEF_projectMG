package com.projectmg.Repositories;

import com.projectmg.Models.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    @Query("select e from Estoque e where e.nome like %:nome%")
    List<Estoque> findByNome(String nome);

    @Query("select e from Estoque e where e.tipo like %:tipo%")
    List<Estoque> findByTipo(String tipo);

    @Query("select e from Estoque e where e.marca like %:marca%")
    List<Estoque> findByMarca(String marca);






}
