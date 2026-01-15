package com.projectmg.Repository.Material;

import com.projectmg.Domain.Entity.Material.Material;
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

    @Query("select e from Material e where e.fornecedor like %:fornecedor%")
    List<Material> findByfornecedor(String fornecedor);

    @Query("select e from Material e where e.ativo = true")
    List<Material> findAllAtivos();

    List<Material> findTop5ByOrderByDataAtualizacaoDesc();

    boolean existsByNomeAndIdNot(String nome, Long id);

}
