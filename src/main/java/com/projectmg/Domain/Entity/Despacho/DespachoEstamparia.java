package com.projectmg.Domain.Entity.Despacho;

import com.projectmg.Domain.Entity.Referencia;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class DespachoEstamparia extends DespachoBase {

    @ManyToOne
    private Referencia referencia;

    private Integer quantidadePecas;

    private String tipoEstampa;

    private String referenciaArte;
}
