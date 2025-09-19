package com.senai.conta_bancaria.domain.entity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("POUPANCA")
@SuperBuilder
@NoArgsConstructor
public class ContaPoupanca extends Conta{
   @Column(precision = 10, scale = 4)
    private Double rendimento;
}