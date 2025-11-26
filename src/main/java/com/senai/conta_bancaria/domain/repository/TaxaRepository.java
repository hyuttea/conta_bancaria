package com.senai.conta_bancaria.domain.repository;


import com.senai.conta_bancaria.domain.entity.TaxaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxaRepository extends JpaRepository<TaxaEntity, String> {
}
