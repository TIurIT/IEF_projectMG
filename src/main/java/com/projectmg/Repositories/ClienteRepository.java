package com.projectmg.Repositories;

import com.projectmg.Models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.nome LIKE %:nome%")
    List<Cliente> findByNomeAll(String nome);

    Cliente findByEmail(String email);
    Cliente findByCpfCnpj(String cpfCnpj);
    Cliente findByNome(String nome);
}
