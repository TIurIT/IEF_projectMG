package com.projectmg.Repository.Despacho;

import com.projectmg.Domain.Entity.Despacho.DespachoCorte;
import com.projectmg.Domain.Entity.Despacho.DespachoCorteRetorno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespachoCorteRetornoRepository extends JpaRepository<DespachoCorteRetorno, Long> {

    boolean existsByDespacho(DespachoCorte despacho);

}
