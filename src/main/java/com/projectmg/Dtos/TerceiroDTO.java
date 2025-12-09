package com.projectmg.Dtos;

import com.projectmg.Enum.TipoServico;
import lombok.Data;

@Data
public class TerceiroDTO {
    private Long id;
    private String nome;
    private String bairro;
    private String telefone;
    private TipoServico tipoServico;

    public TerceiroDTO(){}

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
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public TipoServico getTipoServico() {
        return tipoServico;
    }
    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }
}
