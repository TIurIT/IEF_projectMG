package com.projectmg.Dto;


import com.projectmg.Enum.TipoAcesso;

public class UsuarioDTO {
        private Long id;
    private String nome;
    private String email;
    private String senha;
    private TipoAcesso tipoAcesso;
    private boolean verificado;

    public UsuarioDTO(){}

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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public TipoAcesso getTipoAcesso() {
        return tipoAcesso;
    }
    public void setTipo(TipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }
    public boolean isVerificado() {
        return verificado;
    }
    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }
}
