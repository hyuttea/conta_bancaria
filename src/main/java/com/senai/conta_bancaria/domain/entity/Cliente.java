package com.senai.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Cliente{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "nome não pode ser vazio")
    private String nome;

    @NotBlank(message = "cpf não pode ser vazio")
    @Size(min = 11, max = 11, message = "Tamanho do cpf tem que ser 11")
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    private List<Conta> contas;
}

