package com.senai.conta_bancaria.domain.entity.exceptions;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(String entidade) {
        super(entidade + " não existente ou inativo(a).");
    }
}
