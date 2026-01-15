package com.projectmg.Repository.Despacho;

import com.projectmg.Domain.Entity.Despacho.DespachoBase;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DespachoBaseRepository<T extends DespachoBase>
        extends JpaRepository<T, Long> {
}

