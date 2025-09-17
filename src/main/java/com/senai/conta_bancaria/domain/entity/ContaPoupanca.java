package com.senai.conta_bancaria.domain.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class ContaPoupanca extends Conta{
    @NotNull(message = "rendimento não pode ser nulo")
    private Double rendimento;
}