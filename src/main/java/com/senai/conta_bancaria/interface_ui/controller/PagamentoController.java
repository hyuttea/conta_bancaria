package com.senai.conta_bancaria.interface_ui.controller;


import com.senai.conta_bancaria.aplication.dto.PagamentoDTO;
import com.senai.conta_bancaria.aplication.service.PagamentoAppService;
import com.senai.conta_bancaria.domain.entity.PagamentoEntity;
import com.senai.conta_bancaria.domain.entity.TaxaEntity;
import com.senai.conta_bancaria.domain.service.PagamentoDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoAppService appService;
    private final PagamentoDomainService domainService;

    // REALIZAR PAGAMENTO
    @PostMapping
    public ResponseEntity<PagamentoEntity> realizarPagamento(@RequestBody PagamentoDTO dto) {
        return ResponseEntity.ok(domainService.realizarPagamento(dto));
    }

    // LISTAR TODOS OS PAGAMENTOS
    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> listarPagamentos() {
        return ResponseEntity.ok(appService.listarPagamentos());
    }

    //Acha o boleto e lista ele
    @GetMapping("/boletos")
    public ResponseEntity<List<PagamentoDTO>> listarBoletosAPagar() {
        return ResponseEntity.ok(appService.listarBoletosAPagar());
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> buscarPagamentoPorId(@PathVariable String id) {
        return ResponseEntity.ok(appService.buscarPorId(id));
    }
}

