package com.senai.conta_bancaria.aplication.dto;


import com.senai.conta_bancaria.domain.entity.ClienteEntity;
import java.util.List;

public record ClienteResponseDTO(

        String id,
        String nomeCompleto,
        String cpf,
        List<ContaResumoDTO> contas
) {
    public static ClienteResponseDTO fromEntity(ClienteEntity cliente) {
        List<ContaResumoDTO> contas = cliente.getContas().stream()
                .map(ContaResumoDTO::fromEntity)
                .toList();

        System.out.println("dto"+contas);
        System.out.println("dto"+cliente.getCpf());

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNomeCompleto(),
                cliente.getCpf(),
                contas
        );
    }
}
