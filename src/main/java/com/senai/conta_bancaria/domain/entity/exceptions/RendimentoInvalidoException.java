package com.senai.conta_bancaria.domain.entity.exceptions;

public class RendimentoInvalidoException extends RuntimeException {
    public RendimentoInvalidoException() {
        super("Rendimento aplicado deve ser aplicado somente em conta poupança!");
    }
}
