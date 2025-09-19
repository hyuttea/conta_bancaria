package com.senai.conta_bancaria.aplication.service;
import com.senai.conta_bancaria.aplication.dto.ClienteRegistroDTO;
import com.senai.conta_bancaria.aplication.dto.ClienteResponseDTO;
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

    public ClienteResponseDTO registrarClienteOuAnexarConta(ClienteRegistroDTO dto) {
    }
}