package com.senai.conta_bancaria.domain.repository;


import com.senai.conta_bancaria.domain.entity.GerenteEnity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GerenteRepository extends JpaRepository<GerenteEnity, String> {
    Optional<GerenteEnity> findByEmail(String email);
}
