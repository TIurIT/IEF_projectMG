package com.projectmg.Models;

import com.projectmg.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Table(name= "tb_ordem_producao_terceiro")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdemProducaoTerceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "terceiro_id", nullable = false)
    private Terceiro terceiro;

    @OneToMany
    @JoinColumn(name = "ordem_producao_id", nullable = false)
    private List<OrdemProducao> ordens;

    @Enumerated
    @Column(name = "status")
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrdemProducaoTerceiro that = (OrdemProducaoTerceiro) o;
        return Objects.equals(id, that.id) && Objects.equals(terceiro, that.terceiro)
                && Objects.equals(ordens, that.ordens) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, terceiro, ordens, status);
    }
}
