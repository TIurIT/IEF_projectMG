package Repositories;

import Models.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, UUID> {

    Estoque findByNome(String nome);
    Estoque findByTipo(String tipo);
    Estoque findByMarca(String marca);
}
