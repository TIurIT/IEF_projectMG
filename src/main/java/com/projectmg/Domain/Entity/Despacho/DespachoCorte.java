package com.projectmg.Domain.Entity.Despacho;

import com.projectmg.Domain.Entity.Material.Material;
import com.projectmg.Domain.Entity.Referencia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Table(name = "despacho_corte")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DespachoCorte extends DespachoBase {

    @ManyToOne(optional = false)
    private Referencia referencia;

    @ManyToOne(optional = false)
    private Material material;

    private Integer quantidadePecasSolicitadas;

    private Double quantidadeMaterialEnviado;

    private boolean possuiDivergencia = false;

    private boolean divergenciaResolvida = false;

    private String usuarioCricao;

    @OneToOne(mappedBy = "despacho", cascade = CascadeType.ALL)
    private DespachoCorteRetorno retorno;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DespachoCorte that = (DespachoCorte) o;
        return possuiDivergencia == that.possuiDivergencia && divergenciaResolvida == that.divergenciaResolvida && Objects.equals(referencia, that.referencia) && Objects.equals(material, that.material) && Objects.equals(quantidadePecasSolicitadas, that.quantidadePecasSolicitadas) && Objects.equals(quantidadeMaterialEnviado, that.quantidadeMaterialEnviado) && Objects.equals(usuarioCricao, that.usuarioCricao) && Objects.equals(retorno, that.retorno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), referencia, material, quantidadePecasSolicitadas, quantidadeMaterialEnviado, possuiDivergencia, divergenciaResolvida, usuarioCricao, retorno);
    }
}
