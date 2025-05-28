package com.projectmg.Dto;

import com.projectmg.Enum.TipoAcao;
import com.projectmg.Models.Produto;
import com.projectmg.Security.UsuarioAuditoria;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;



import java.time.LocalDate;

@Data
public class ProdutoDTO {

    private Long id;
    private String nome;
    private String referencia;
    private LocalDate dataAtualizacao;
    private String usuarioUltimaAlteracao;
    private TipoAcao acao;

    @PrePersist
    @PreUpdate
    public void atualizarDataAtualizacao() {
        this.dataAtualizacao = LocalDate.now();
        this.usuarioUltimaAlteracao = UsuarioAuditoria.getUsuarioLogado();
    }

    public ProdutoDTO(){}

    public ProdutoDTO(Long id, String nome, String referencia, LocalDate dataAtualizacao, String usuarioUltimaAlteracao, TipoAcao acao) {
        this.id = id;
        this.nome = nome;
        this.referencia = referencia;
        this.dataAtualizacao = dataAtualizacao;
        this.usuarioUltimaAlteracao = usuarioUltimaAlteracao;
        this.acao = acao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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
}
