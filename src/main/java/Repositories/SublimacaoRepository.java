package Repositories;

import Models.Sublimacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SublimacaoRepository extends JpaRepository <Sublimacao, UUID> {
}
