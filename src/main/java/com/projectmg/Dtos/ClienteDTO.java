package com.projectmg.Dtos;

import lombok.Data;

@Data
public class ClienteDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpfCnpj;

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

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpf_cnpj) {
        this.cpfCnpj = cpf_cnpj;
    }
}
