package com.projectmg.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectmg.Enum.TipoAcao;
import com.projectmg.Models.HistoricoMaterial;
import com.projectmg.Security.UsuarioAuditoria;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MaterialDTO {
    private Long id;
    private String tipo;
    private String nome;
    private String marca;
    private Integer quantidade;
    private LocalDate dataDeCriacao;
    private LocalDate dataAtualizacao;
    private String usuarioUltimaAlteracao;
    private TipoAcao acao;

    @JsonIgnore
    private List<HistoricoMaterial> historicoMaterialList;

    @PrePersist
    @PreUpdate
    public void atualizarDataAtualizacao() {
        this.dataAtualizacao = LocalDate.now();
        this.usuarioUltimaAlteracao = UsuarioAuditoria.getUsuarioLogado();
    }

    public MaterialDTO(Long id, String tipo, String nome, String marca, Integer quantidade, LocalDate dataDeCriacao, LocalDate dataAtualizacao, String usuarioUltimaAlteracao, TipoAcao acao, List<HistoricoMaterial> historicoMaterialList) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.marca = marca;
        this.quantidade = quantidade;
        this.dataDeCriacao = dataDeCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.usuarioUltimaAlteracao = usuarioUltimaAlteracao;
        this.acao = acao;
        this.historicoMaterialList = historicoMaterialList;
    }

    public MaterialDTO(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public LocalDate getDataDeCriacao() {
        return dataDeCriacao;
    }
    public void setDataDeCriacao(LocalDate dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }
    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }
    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
    public String getUsuarioUltimaAlteracao() {
        return usuarioUltimaAlteracao;
    }
    public void setUsuarioUltimaAlteracao(String usuarioUltimaAlteracao) {
        this.usuarioUltimaAlteracao = usuarioUltimaAlteracao;
    }
    public TipoAcao getAcao() {
        return acao;
    }
    public void setAcao(TipoAcao acao) {
        this.acao = acao;
    }
    public List<HistoricoMaterial> getHistoricoMaterialList() {
        return historicoMaterialList;
    }
    public void setHistoricoMaterialList(List<HistoricoMaterial> historicoMaterialList) {
        this.historicoMaterialList = historicoMaterialList;
    }
}
