package com.senai.conta_bancaria.aplication.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ValorSaqueDepositoDTO(
        @NotNull(message = "O valor não pode ser nulo")
        BigDecimal valor
) {
}

