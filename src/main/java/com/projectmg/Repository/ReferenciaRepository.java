package com.projectmg.Repository;

import com.projectmg.Domain.Entity.Referencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferenciaRepository extends JpaRepository<Referencia, Long> {

    @Query("SELECT p FROM Referencia p WHERE p.nome LIKE %:nome%")
    List<Referencia> findByNome(String nome);

    @Query("SELECT p FROM Referencia p WHERE p.referencia LIKE %:referencia%")
    List<Referencia> findByReferencia(String referencia);

    List<Referencia> findTop5ByOrderByDataAtualizacaoDesc();

    @Query("SELECT p FROM Referencia p WHERE p.acao != 'DELETADO'")
    List<Referencia> findAllAtivos();

    boolean existsByNomeAndIdNot(String nome, Long id);
    boolean existsByReferenciaAndIdNot(String referencia, Long id);
    List<Referencia> findByAtivoTrue();


}
