package com.senai.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@MappedSuperclass
@Data
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotNull(message = "numero não pode ser nulo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numero;
    @NotNull(message = "saldo não pode ser nulo")
    private Double saldo;
    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;
}
