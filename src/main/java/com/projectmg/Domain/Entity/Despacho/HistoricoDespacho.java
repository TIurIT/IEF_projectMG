package com.projectmg.Domain.Entity.Despacho;

import com.projectmg.Domain.Enum.TipoServico;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoDespacho {

    @Id
    @GeneratedValue
    private Long id;

    private Long despachoId;

    private TipoServico tipoServico;

    private Long terceiroId;

    private LocalDateTime data;

    private String usuario;

    private String acao;

    @PrePersist
    public void prePersist() {
        data = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoDespacho that = (HistoricoDespacho) o;
        return Objects.equals(id, that.id) && Objects.equals(despachoId, that.despachoId) && tipoServico == that.tipoServico && Objects.equals(terceiroId, that.terceiroId) && Objects.equals(data, that.data) && Objects.equals(usuario, that.usuario) && Objects.equals(acao, that.acao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, despachoId, tipoServico, terceiroId, data, usuario, acao);
    }
}
