package com.senai.conta_bancaria.domain.repository;


import com.senai.conta_bancaria.domain.entity.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<ContaEntity, String> {
    List <ContaEntity> findAllByAtivoTrue();

    Optional <ContaEntity> findByNumeroAndAtivoTrue(String numero);
}
