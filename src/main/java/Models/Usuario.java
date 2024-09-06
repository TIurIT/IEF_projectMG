package Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "usuario")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "tipo")
    private int tipo;

    @ManyToOne
    @JoinColumn(name = "costureira_id")
    private Costureira costureira;

    @ManyToOne
    @JoinColumn(name = "estamparia_id")
    private Estamparia estamparia;

    @ManyToOne
    @JoinColumn(name = "sublimacao_id")
    private Sublimacao sublimacao;
}
