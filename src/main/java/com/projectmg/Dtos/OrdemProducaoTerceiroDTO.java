package com.projectmg.Dtos;

import com.projectmg.Enum.Status;
import com.projectmg.Models.OrdemProducao;
import com.projectmg.Models.Terceiro;
import lombok.Data;

import java.util.List;

@Data
public class OrdemProducaoTerceiroDTO {

    private Long id;
    private Terceiro terceiro;
    private List<OrdemProducao> ordens;
    private Status status;

    public OrdemProducaoTerceiroDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Terceiro getTerceiro() { return terceiro; }
    public void setTerceiro(Terceiro terceiro) { this.terceiro = terceiro; }
    public List<OrdemProducao> getOrdens() { return ordens; }
    public void setOrdens(List<OrdemProducao> ordemProducaos) { this.ordens = ordemProducaos; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
