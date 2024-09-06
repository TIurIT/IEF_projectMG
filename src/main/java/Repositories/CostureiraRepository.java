package Repositories;

import Models.Costureira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CostureiraRepository extends JpaRepository <Costureira, UUID> {
}
