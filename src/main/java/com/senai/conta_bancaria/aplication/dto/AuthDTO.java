package com.senai.conta_bancaria.aplication.dto;

public record AuthDTO() {
    public record LoginRequest(
            String email,
            String senha
    ) {}
    public record TokenResponse(
            String token
    ) {}
}
