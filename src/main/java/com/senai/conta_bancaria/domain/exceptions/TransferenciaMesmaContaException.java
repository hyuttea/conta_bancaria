package com.senai.conta_bancaria.domain.exceptions;

public class TransferenciaMesmaContaException extends RuntimeException {
    public TransferenciaMesmaContaException() {
        super("Impossivel realizar transferencia para a mesma conta: ");
    }
}
