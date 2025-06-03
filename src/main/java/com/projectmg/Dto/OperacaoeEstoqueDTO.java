package com.projectmg.Dto;

import com.projectmg.Enum.TipoAcao;
import lombok.Data;

@Data
public class OperacaoeEstoqueDTO {
    private Long id;
    private int quantidade;
    private String comentario;
    private TipoAcao acao; // "ADICIONADO" ou "RETIRADO"

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public TipoAcao getAcao() {
        return acao;
    }
    public void setAcao(TipoAcao acao) {
        this.acao = acao;
    }
}
