package com.projectmg.Repositories;

import com.projectmg.Models.OrdemProducaoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemProducaoItemRepository extends JpaRepository<OrdemProducaoItem, Long> {

}
