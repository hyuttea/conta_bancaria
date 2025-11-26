package com.senai.conta_bancaria.domain.repository;


import com.senai.conta_bancaria.domain.entity.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<ServicoEntity, Long> {

}
