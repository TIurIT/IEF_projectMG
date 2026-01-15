package com.projectmg.Repository.Despacho;

import com.projectmg.Domain.Entity.Despacho.HistoricoDespacho;
import com.projectmg.Domain.Enum.TipoServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoDespachoRepository extends JpaRepository<HistoricoDespacho, Long> {

    List<HistoricoDespacho> findByDespachoIdAndTipoServico(
            Long despachoId,
            TipoServico tipoServico
    );
}
