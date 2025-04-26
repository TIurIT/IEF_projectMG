package com.projectmg.Repositories;

import com.projectmg.Dto.MaterialDTO;
import com.projectmg.Models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    @Query("select e from Material e where e.nome like %:nome%")
    List<Material> findByNome(String nome);

    @Query("select e from Material e where e.tipo like %:tipo%")
    List<Material> findByTipo(String tipo);

    @Query("select e from Material e where e.marca like %:marca%")
    List<Material> findByMarca(String marca);

    @Query("select e from Material e")
    List<Material> findAll();






}
