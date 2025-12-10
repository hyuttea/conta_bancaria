package com.senai.conta_bancaria.aplication.service;


import com.senai.conta_bancaria.aplication.dto.ClienteCadastroDTO;
import com.senai.conta_bancaria.aplication.dto.ClienteResponseDTO;
import com.senai.conta_bancaria.domain.entity.ClienteEntity;
import com.senai.conta_bancaria.domain.enums.Role;
import com.senai.conta_bancaria.domain.exceptions.ContaMesmoTipoException;
import com.senai.conta_bancaria.domain.exceptions.EntidadeNaoEncontradaException;
import com.senai.conta_bancaria.domain.repository.ClienteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
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
    private final PasswordEncoder passwordEncoder;
    public ClienteResponseDTO registarClienteOuAnexarConta(ClienteCadastroDTO dto) {

        var cliente = repository.findByCpf(dto.cpf()).orElseGet(
                () -> repository.save(dto.toEntity())
        );


        var contas = cliente.getContas();
        var novaConta = dto.contaDTO().toEntity(cliente);

        boolean jaTemTipo = contas.stream()
                .anyMatch(c -> c.getClass().equals(novaConta.getClass()) && c.isAtivo());

        if(jaTemTipo)
            throw new ContaMesmoTipoException();

        cliente.getContas().add(novaConta);

        cliente.setSenha(passwordEncoder.encode(dto.senha()));
        return ClienteResponseDTO.fromEntity(repository.save(cliente));
    }
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listarClientesAtivos() {

        List<ClienteResponseDTO> lista = repository.findAll().stream()
                .map(ClienteResponseDTO::fromEntity)
                .toList();

        System.out.println(repository.findAll());

        return lista;
    }
    @Transactional(readOnly = true)
    public ClienteResponseDTO buscarClienteAtivoPorCpf(String cpf) {
        var cliente = buscarPorCpfCliente(cpf);
        System.out.println(cliente);
        return ClienteResponseDTO.fromEntity(cliente);
    }

    public ClienteResponseDTO atualizarCliente(String cpf, ClienteCadastroDTO dto) {
        var cliente = buscarPorCpfCliente(cpf);

        cliente.setNomeCompleto(dto.nomeCompleto());
        cliente.setCpf(dto.cpf());

        return ClienteResponseDTO.fromEntity(repository.save(cliente));
    }

    public void deletarCliente(String cpf) {
        var cliente = buscarPorCpfCliente(cpf);

        cliente.setAtivo(false);
        cliente.getContas().forEach(c -> c.setAtivo(false));

        repository.save(cliente);
    }

    private ClienteEntity buscarPorCpfCliente(String cpf) {
        var cliente = repository.findByCpf(cpf)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente")); //Só deleta caso o cliente pedir, caso contrário ele constinua inativo
        return cliente;
    }
}
