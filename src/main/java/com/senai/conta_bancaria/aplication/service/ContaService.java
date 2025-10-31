package com.senai.conta_bancaria.aplication.service;
import com.senai.conta_bancaria.aplication.dto.ContaAtualizacaoDTO;
import com.senai.conta_bancaria.aplication.dto.ContaAutualizacaoDTO;
import com.senai.conta_bancaria.aplication.dto.ContaResumoDTO;
import com.senai.conta_bancaria.aplication.dto.TransferenciaDTO;
import com.senai.conta_bancaria.aplication.dto.ValorSaqueDepositoDTO;
import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.entity.ContaCorrente;
import com.senai.conta_bancaria.domain.entity.ContaPoupanca;
import com.senai.conta_bancaria.domain.exceptions.EntidadeNaoEncontradaException;
import com.senai.conta_bancaria.domain.exceptions.RendimentoInvalidoException;
import com.senai.conta_bancaria.domain.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

//Service so faz chamada de metodo
@Service
@RequiredArgsConstructor
@Transactional
public class ContaService {

    private final ContaRepository repository;

    @Transactional(readOnly = true)
    public List<ContaResumoDTO> listarTodasContas() {
        return repository.findAllByAtivoTrue().stream()
                .map(ContaResumoDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ContaResumoDTO buscarContaPorNumero(String numero) {
        return ContaResumoDTO.fromEntity(
                repository.findByNumeroAndAtivoTrue(numero)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Conta"))
        );
    }

    public ContaResumoDTO atualizarConta(String numeroDaConta, ContaAutualizacaoDTO dto) {
        var conta = buscaContaAtivaPorNumero(numeroDaConta);

        if (conta instanceof ContaPoupanca poupanca){
            poupanca.setRendimento(dto.rendimento());
        } else if (conta instanceof ContaCorrente corrente) {
            corrente.setLimite(dto.limite());
            corrente.setTaxa(dto.taxa());
        }
        conta.setSaldo(dto.saldo());

        return ContaResumoDTO.fromEntity(repository.save(conta));
    }

    public void deletarConta(String numeroDaConta) {
        var conta = buscaContaAtivaPorNumero(numeroDaConta);
        conta.setAtivo(false);
        repository.save(conta);
    }

    //Metodo para evitar repetição de código = clean code
    private Conta buscaContaAtivaPorNumero(String numeroDaConta) {
        var conta = repository.findByNumeroAndAtivoTrue(numeroDaConta)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Conta"));
        return conta;
    }

    public ContaResumoDTO sacar(String numeroDaConta, BigDecimal valor) {
        var conta = buscaContaAtivaPorNumero(numeroDaConta);
        conta.sacar(valor);
        return ContaResumoDTO.fromEntity(repository.save(conta));
    }

    public ContaResumoDTO depositar(String numeroDaConta, BigDecimal dto) {
        var conta = buscaContaAtivaPorNumero(numeroDaConta);
        conta.depositar(dto);
        return ContaResumoDTO.fromEntity(repository.save(conta));
    }

    public ContaResumoDTO transferir(String numeroDaConta, TransferenciaDTO dto) {
        var contaOrigem = buscaContaAtivaPorNumero(numeroDaConta);
        var contaDestino = buscaContaAtivaPorNumero(dto.contaDestino());
        contaOrigem.transferir(dto.valor(), contaDestino);
        repository.save(contaDestino);
        return ContaResumoDTO.fromEntity(repository.save(contaOrigem));
    }

    public ContaResumoDTO aplicarRendimento(String numeroDaConta) {
        var conta = buscaContaAtivaPorNumero(numeroDaConta);
        if(conta instanceof ContaPoupanca poupanca){
            poupanca.aplicarRendimento();
            return ContaResumoDTO.fromEntity(repository.save(conta));
        }
        throw new RendimentoInvalidoException();
    }
}