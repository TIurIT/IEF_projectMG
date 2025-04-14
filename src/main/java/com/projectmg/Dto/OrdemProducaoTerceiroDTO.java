package com.projectmg.Dto;

import com.projectmg.Models.OrdemProducao;
import com.projectmg.Models.Terceiro;

import java.util.List;

public class OrdemProducaoTerceiroDTO {

    private Long id;
    private Terceiro terceiro;
    private List<OrdemProducao> ordemProducaos;

    public OrdemProducaoTerceiroDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Terceiro getTerceiro() { return terceiro; }
    public void setTerceiro(Terceiro terceiro) { this.terceiro = terceiro; }
    public List<OrdemProducao> getOrdemProducaos() { return ordemProducaos; }
    public void setOrdemProducaos(List<OrdemProducao> ordemProducaos) { this.ordemProducaos = ordemProducaos; }

}
