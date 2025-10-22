package com.senai.conta_bancaria.domain.exceptions;

public class TipoDeContaInvalidaException extends RuntimeException {
    public TipoDeContaInvalidaException() {
        super("Tipo de conta inválido. Deve ser 'CORRENTE' ou 'POUPANÇA'.");
    }
}
