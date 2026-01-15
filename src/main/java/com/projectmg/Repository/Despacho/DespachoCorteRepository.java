package com.projectmg.Repository.Despacho;

import com.projectmg.Domain.Entity.Despacho.DespachoCorte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DespachoCorteRepository extends JpaRepository<DespachoCorte, Long> {

    List<DespachoCorte> findByRetornoIsNull();

    List<DespachoCorte> findByPossuiDivergenciaTrueAndDivergenciaResolvidaFalse();
}
