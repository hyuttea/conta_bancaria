package com.senai.conta_bancaria.domain.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class ContaCorrente extends Conta{
    @NotNull(message = "limite não pode ser nulo")
    private Double limite;
    @NotNull(message = "taxa não pode ser nulo")
    private Double taxa;
}