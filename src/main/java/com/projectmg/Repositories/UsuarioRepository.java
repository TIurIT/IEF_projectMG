package com.projectmg.Repositories;

import com.projectmg.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    Usuario findByEmail(String email);
}
