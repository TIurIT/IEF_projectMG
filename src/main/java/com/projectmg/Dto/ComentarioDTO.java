package com.projectmg.Dto;

import lombok.Data;

@Data
public class ComentarioDTO {

    private Long id;
    private String comentario;
    private Long historicoId;

    public ComentarioDTO() {
    }

    public ComentarioDTO(Long id, String comentario, Long historicoId) {
        this.id = id;
        this.comentario = comentario;
        this.historicoId = historicoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getHistoricoId() {
        return historicoId;
    }

    public void setHistoricoId(Long historicoId) {
        this.historicoId = historicoId;
    }
}
