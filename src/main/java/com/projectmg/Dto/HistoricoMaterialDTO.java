package com.projectmg.Dto;

import com.projectmg.Enum.TipoAcao;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HistoricoMaterialDTO {
    private Long id;
    private Long materialId;
    private String materialNome;
    private Integer quantidade;
    private String comentario;
    private LocalDate dataHistorico;
    private TipoAcao acao;
    private String usuarioUltimaAlteracao;

    public HistoricoMaterialDTO() {
    }

    public HistoricoMaterialDTO(Long id, Long materialId, String materialNome, Integer quantidade, String comentario, LocalDate dataHistorico, TipoAcao acao, String usuarioUltimaAlteracao) {
        this.id = id;
        this.materialId = materialId;
        this.materialNome = materialNome;
        this.quantidade = quantidade;
        this.comentario = comentario;
        this.dataHistorico = dataHistorico;
        this.acao = acao;
        this.usuarioUltimaAlteracao = usuarioUltimaAlteracao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialNome() {
        return materialNome;
    }

    public void setMaterialNome(String materialNome) {
        this.materialNome = materialNome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getDataHistorico() {
        return dataHistorico;
    }

    public void setDataHistorico(LocalDate dataHistorico) {
        this.dataHistorico = dataHistorico;
    }

    public TipoAcao getAcao() {
        return acao;
    }

    public void setAcao(TipoAcao acao) {
        this.acao = acao;
    }

    public String getUsuarioUltimaAlteracao() {
        return usuarioUltimaAlteracao;
    }

    public void setUsuarioUltimaAlteracao(String usuarioUltimaAlteracao) {
        this.usuarioUltimaAlteracao = usuarioUltimaAlteracao;
    }
}
