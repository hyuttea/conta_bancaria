package com.senai.conta_bancaria.domain.exceptions;

public class TaxaInvalidaException extends RuntimeException {
    public TaxaInvalidaException() {
        super("A taxa aplicada é inválida");
    }
}
