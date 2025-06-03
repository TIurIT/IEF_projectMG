package com.projectmg.Dto;

import com.projectmg.Enum.TipoAcao;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HistoricoMaterialDTO {
    private Long id;
    private Long materialId;
    private Integer quantidadeAlterada;
    private LocalDate dataHistorico;
    private TipoAcao acao;
    private String usuarioUltimaAlteracao;
    private List<String> comentarios;

    public HistoricoMaterialDTO() {
    }

    public HistoricoMaterialDTO(Long id, Long materialId, Integer quantidadeAlterada, LocalDate dataHistorico, TipoAcao acao, String usuarioUltimaAlteracao, List<String> comentarios) {
        this.id = id;
        this.materialId = materialId;
        this.quantidadeAlterada = quantidadeAlterada;
        this.dataHistorico = dataHistorico;
        this.acao = acao;
        this.usuarioUltimaAlteracao = usuarioUltimaAlteracao;
        this.comentarios = comentarios;
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

    public Integer getQuantidadeAlterada() {
        return quantidadeAlterada;
    }

    public void setQuantidadeAlterada(Integer quantidadeAlterada) {
        this.quantidadeAlterada = quantidadeAlterada;
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

    public List<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }
}
