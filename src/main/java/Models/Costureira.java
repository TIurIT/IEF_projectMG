package Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "costureira")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Costureira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "telefone")
    private String telefone;
}
