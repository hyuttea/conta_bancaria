package com.senai.conta_bancaria.domain.entity.exceptions;

public class TipoDeContaInvalidaException extends RuntimeException {
    public TipoDeContaInvalidaException() {
        super("Tipo de conta inválido. Deve ser 'CORRENTE' ou 'POUPANÇA'.");
    }
}
