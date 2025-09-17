package com.senai.conta_bancaria.aplication.service;

import com.senai.conta_bancaria.aplication.dto.ClienteDTO;
import com.senai.conta_bancaria.aplication.dto.CriarClienteDTO;
import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.entity.ContaCorrente;
import com.senai.conta_bancaria.domain.entity.ContaPoupanca;
import com.senai.conta_bancaria.domain.entity.TipoConta;
import com.senai.conta_bancaria.domain.repository.ClienteRepository;
import com.senai.conta_bancaria.domain.repository.ContaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;

    public ClienteService(ClienteRepository clienteRepository, ContaRepository contaRepository) {
        this.clienteRepository = clienteRepository;
        this.contaRepository = contaRepository;
    }

    public ClienteDTO salvarCliente(CriarClienteDTO dto) {
        Cliente salvo = clienteRepository.save(dto.toEntity());

        if(dto.tipoConta() == TipoConta.CONTA_CORRENTE){
            ContaCorrente contaCorrente = new ContaCorrente();
            contaCorrente.setSaldo(0.0);
            contaCorrente.setCliente(salvo);
            contaCorrente.setLimite(dto.limite());
            contaCorrente.setTaxa(dto.taxa());
            contaRepository.save(contaCorrente);
        }

        if(dto.tipoConta() == TipoConta.CONTA_POUPANCA){
            ContaPoupanca contaPoupanca = new ContaPoupanca();
            contaPoupanca.setSaldo(0.0);
            contaPoupanca.setCliente(salvo);
            contaPoupanca.setRendimento(dto.rendimento());
            contaRepository.save(contaPoupanca);
        }
        return ClienteDTO.fromEntity(salvo);
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteDTO buscarClientePorId(String id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        return ClienteDTO.fromEntity(cliente);
    }

    public ClienteDTO atualizarCliente(String id, ClienteDTO dto) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()) return null;

        Cliente existente = clienteOptional.get();
        existente.setNome(dto.nome());
        existente.setCpf(dto.cpf());
        existente.setContas(dto.contas());

        Cliente atualizado = clienteRepository.save(existente);
        return ClienteDTO.fromEntity(atualizado);
    }

    public void deletarCliente(String id) {
        clienteRepository.deleteById(id);
    }
}