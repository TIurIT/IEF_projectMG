package com.projectmg.Dto;

import com.projectmg.Security.UsuarioAuditoria;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProdutoDTO {

    private Long id;
    private String nome;
    private String referencia;
    private LocalDateTime dataAtualizacao;
    private String usuarioUltimaAlteracao;

    @PrePersist
    @PreUpdate
    public void atualizarDataAtualizacao() {
        this.dataAtualizacao = LocalDateTime.now();
        this.usuarioUltimaAlteracao = UsuarioAuditoria.getUsuarioLogado();
    }


    public ProdutoDTO(){}

    public ProdutoDTO(Long id, String nome, String referencia, LocalDateTime dataAtualizacao, String usuarioUltimaAlteracao) {
        this.id = id;
        this.nome = nome;
        this.referencia = referencia;
        this.dataAtualizacao = dataAtualizacao;
        this.usuarioUltimaAlteracao = usuarioUltimaAlteracao;
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

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getUsuarioUltimaAlteracao() {
        return usuarioUltimaAlteracao;
    }

    public void setUsuarioUltimaAlteracao(String usuarioUltimaAlteracao) {
        this.usuarioUltimaAlteracao = usuarioUltimaAlteracao;
    }
}
