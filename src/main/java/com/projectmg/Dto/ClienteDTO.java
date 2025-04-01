package com.projectmg.Dto;

public class ClienteDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String Cpf_Cnpj;

    public ClienteDTO(){}

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf_cnpj() {
        return Cpf_Cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        Cpf_Cnpj = cpf_cnpj;
    }
}
