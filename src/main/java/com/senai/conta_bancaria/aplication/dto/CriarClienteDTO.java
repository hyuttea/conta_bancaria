package com.senai.conta_bancaria.aplication.dto;

import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.entity.TipoConta;

public record CriarClienteDTO(
        String id,
        String nome,
        String cpf,
        TipoConta tipoConta,
        Double limite,
        Double taxa,
        Double rendimento
) {

    public Cliente toEntity() {
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);
        return cliente;
    }
}
