package com.senai.conta_bancaria.aplication.dto;

import java.math.BigDecimal;

public record TransferenciaDTO(
        BigDecimal valor,
        String contaDestino
) {
}
