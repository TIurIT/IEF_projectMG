package com.projectmg.Domain.Entity.Despacho;

import com.projectmg.Domain.Entity.Referencia;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class DespachoCostura extends DespachoBase {

    @ManyToOne
    private Referencia referencia;

    private Integer quantidadePecas;

    private LocalDate previsaoRetorno;

}
