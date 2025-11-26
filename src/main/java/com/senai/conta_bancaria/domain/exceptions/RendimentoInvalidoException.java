package com.senai.conta_bancaria.domain.exceptions;

public class RendimentoInvalidoException extends RuntimeException {
    public RendimentoInvalidoException() {
        super("O rendimento aplicado somente em contas do tipo Poupança.");
    }
}
