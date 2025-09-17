package com.senai.conta_bancaria.aplication.service;

import com.senai.conta_bancaria.aplication.dto.ContaDTO;
import com.senai.conta_bancaria.aplication.dto.ValorDTO;
import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.entity.ContaCorrente;
import com.senai.conta_bancaria.domain.repository.ClienteRepository;
import com.senai.conta_bancaria.domain.repository.ContaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContaService {
    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    public ContaDTO salvarConta(ContaDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElse(null);
        Conta salvo = contaRepository.save(dto.toEntity(cliente));
        return ContaDTO.fromEntity(salvo);
    }

    @Transactional(readOnly = true)
    public List<ContaDTO> listarConta() {
        return contaRepository.findAll()
                .stream()
                .map(ContaDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ContaDTO buscarContaPorId(String id) {
        Optional<Conta> contaOptional = contaRepository.findById(id);
        if (contaOptional.isEmpty()) return null;

        Conta conta = contaOptional.get();

        return ContaDTO.fromEntity(conta);
    }

    public ContaDTO atualizarConta(String id, ContaDTO dto) {
        Optional<Conta> contaOptional = contaRepository.findById(id);
        if (contaOptional.isEmpty()) return null;

        Conta existente = contaOptional.get();
        existente.setNumero(dto.numero());
        existente.setSaldo(dto.saldo());

        if (dto.clienteId() != null) {
            Optional<Cliente> clienteOptional = clienteRepository.findById(dto.clienteId());
            clienteOptional.ifPresent(existente::setCliente);
        } else {
            existente.setCliente(null);
        }

       if(existente instanceof ContaCorrente){
           ContaCorrente contaCorrente = (ContaCorrente) existente;
        }

        Conta atualizado = contaRepository.save(existente);
        return ContaDTO.fromEntity(atualizado);
    }

    public ContaDTO depositar(String id, ValorDTO dto){
        Optional<Conta> contaOptional = contaRepository.findById(id);
        if (contaOptional.isEmpty()) return null;

        Conta existente = contaOptional.get();
        existente.setSaldo(existente.getSaldo() + dto.valor());

        return ContaDTO.fromEntity(existente);
    }

    public void deletarConta(String id) {
        contaRepository.deleteById(id);
    }
}
