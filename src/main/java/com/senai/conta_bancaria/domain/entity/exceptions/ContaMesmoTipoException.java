package com.senai.conta_bancaria.domain.entity.exceptions;

public class ContaMesmoTipoException extends RuntimeException {
    public ContaMesmoTipoException() {
        super("O cliente já possui uma conta do mesmo tipo.");
    }
}
