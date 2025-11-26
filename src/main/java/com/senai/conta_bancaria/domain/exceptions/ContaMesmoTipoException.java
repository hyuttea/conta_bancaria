package com.senai.conta_bancaria.domain.exceptions;

public class ContaMesmoTipoException extends RuntimeException {
    public ContaMesmoTipoException() {
        super("Uma cliente não pode ter mais de uma conta do mesmo tipo.");
    }
}
