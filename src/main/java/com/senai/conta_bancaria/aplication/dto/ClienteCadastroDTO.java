package com.senai.conta_bancaria.aplication.dto;



import com.senai.conta_bancaria.domain.entity.ClienteEntity;
import com.senai.conta_bancaria.domain.entity.ContaEntity;
import com.senai.conta_bancaria.domain.enums.Role;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;

public record ClienteCadastroDTO(
        @NotBlank(message = "Este espaço não pode ficar em branco")
        @Size(max = 80, message = "O nome não pode ultrapassar 80 caracteres")
        @Column(nullable = false, length = 80)
        String nomeCompleto,

        @NotBlank(message = "Este espaço não pode ficar em branco")
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato 000.000.000-00")
        @Column(nullable = false, unique = true, length = 14)
        String cpf,

        @Valid
        ContaResumoDTO contaDTO,

        @NotBlank
        @NotNull
        String email,

        @NotBlank
        @NotNull
        String senha
) {

    public ClienteEntity toEntity() {
        return ClienteEntity.builder()
                .ativo(true)
                .nomeCompleto(this.nomeCompleto)
                .cpf(this.cpf)
                .contas(new ArrayList<ContaEntity>())
                .email(this.email)
                .senha(this.senha)
                .role(Role.CLIENTE)
                .build();
    }

}
