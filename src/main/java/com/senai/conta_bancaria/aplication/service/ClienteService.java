package com.senai.conta_bancaria.aplication.service;


import com.senai.conta_bancaria.aplication.dto.ClienteCadastroDTO;
import com.senai.conta_bancaria.aplication.dto.ClienteResponseDTO;
import com.senai.conta_bancaria.domain.entity.ClienteEntity;
import com.senai.conta_bancaria.domain.enums.Role;
import com.senai.conta_bancaria.domain.exceptions.ContaMesmoTipoException;
import com.senai.conta_bancaria.domain.exceptions.EntidadeNaoEncontradaException;
import com.senai.conta_bancaria.domain.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//Isso que é um bean de serviço
//Responsável pela lógica de negócio
@Service
@RequiredArgsConstructor
@Transactional
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteResponseDTO registarClienteOuAnexarConta(ClienteCadastroDTO dto) {

        var cliente = repository.findByCpfAndAtivoTrue(dto.cpf()).orElseGet(
                () -> repository.save(dto.toEntity())
        );

        System.out.println(cliente);

        var contas = cliente.getContas();
        var novaConta = dto.contaDTO().toEntity(cliente);

        boolean jaTemTipo = contas.stream()
                .anyMatch(c -> c.getClass().equals(novaConta.getClass()) && c.isAtivo());

        if(jaTemTipo)
            throw new ContaMesmoTipoException();

        cliente.getContas().add(novaConta);

        System.out.println(cliente.getContas());

        System.out.println(repository.save(cliente));

        System.out.println(ClienteResponseDTO.fromEntity(cliente));

        return ClienteResponseDTO.fromEntity(repository.save(cliente));
    }

    public List<ClienteResponseDTO> listarClientesAtivos() {
        return repository.findAllByAtivoTrue().stream()
                .map(ClienteResponseDTO::fromEntity)
                .toList();
    }

    public ClienteResponseDTO buscarClienteAtivoPorCpf(String cpf) {
        var cliente = buscarPorCpfClienteAtivo(cpf);
        return ClienteResponseDTO.fromEntity(cliente);
    }

    public ClienteResponseDTO atualizarCliente(String cpf, ClienteCadastroDTO dto) {
        var cliente = buscarPorCpfClienteAtivo(cpf);

        cliente.setNomeCompleto(dto.nomeCompleto());
        cliente.setCpf(dto.cpf());

        return ClienteResponseDTO.fromEntity(repository.save(cliente));
    }

    public void deletarCliente(String cpf) {
        var cliente = buscarPorCpfClienteAtivo(cpf);

        cliente.setAtivo(false);
        cliente.getContas().forEach(c -> c.setAtivo(false));

        repository.save(cliente);
    }

    private ClienteEntity buscarPorCpfClienteAtivo(String cpf) {
        var cliente = repository.findByCpfAndAtivoTrue(cpf)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente")); //Só deleta caso o cliente pedir, caso contrário ele constinua inativo
        return cliente;
    }
}
