package com.projectmg.Repositories;

import com.projectmg.Models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE p.nome LIKE %:nome%")
    List<Produto> findByNome(String nome);

    @Query("SELECT p FROM Produto p WHERE p.referencia LIKE %:referencia%")
    List<Produto> findByReferencia(String referencia);

    List<Produto> findTop5ByOrderByDataAtualizacaoDesc();

    @Query("SELECT p FROM Produto p WHERE p.acao != 'DELETADO'")
    List<Produto> findAllAtivos();

    boolean existsByNomeAndIdNot(String nome, Long id);
    boolean existsByReferenciaAndIdNot(String referencia, Long id);

}
