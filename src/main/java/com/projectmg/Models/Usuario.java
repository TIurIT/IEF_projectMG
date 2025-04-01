package com.projectmg.Models;

import com.projectmg.Enum.TipoAcesso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Table(name = "usuario")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "tipoAcesso")
    private TipoAcesso tipoAcesso;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nome, usuario.nome)
                && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha)
                && tipoAcesso == usuario.tipoAcesso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, senha, tipoAcesso);
    }
}
