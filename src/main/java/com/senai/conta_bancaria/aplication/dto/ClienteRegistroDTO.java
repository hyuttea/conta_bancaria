package com.senai.conta_bancaria.aplication.dto;

public record ClienteRegistroDTO(
        String nome,
        String cpf,

        ContaResumoDTO contaDTO
        ) {

}
