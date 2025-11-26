package com.senai.conta_bancaria.aplication.service;

import com.senai.conta_bancaria.aplication.dto.PagamentoDTO;
import com.senai.conta_bancaria.aplication.dto.PagamentoMapper;
import com.senai.conta_bancaria.domain.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class PagamentoAppService {

    private final PagamentoRepository repository;
    private final PagamentoMapper mapper;

    @GetMapping
    public List<PagamentoDTO> listarPagamentos(){
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public List <PagamentoDTO> listarBoletosAPagar(){
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public PagamentoDTO buscarPorId(String id) {
        return mapper.toDTO(
                repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Pagamento com ID " + id + " não encontrado."))
        );
    }
}
