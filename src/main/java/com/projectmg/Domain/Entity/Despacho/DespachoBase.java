package com.projectmg.Domain.Entity.Despacho;

import com.projectmg.Domain.Entity.Terceiro;
import com.projectmg.Domain.Enum.TipoServico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class DespachoBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Terceiro terceiro;

    private LocalDateTime dataDespacho;

    private String usuarioResponsavel;

    private String observacao;

    @Enumerated(EnumType.STRING)
    private TipoServico tipoServico;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DespachoBase that = (DespachoBase) o;
        return Objects.equals(id, that.id) && Objects.equals(terceiro, that.terceiro) && Objects.equals(dataDespacho, that.dataDespacho) && Objects.equals(usuarioResponsavel, that.usuarioResponsavel) && Objects.equals(observacao, that.observacao) && tipoServico == that.tipoServico;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, terceiro, dataDespacho, usuarioResponsavel, observacao, tipoServico);
    }
}
