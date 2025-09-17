package com.senai.conta_bancaria.interface_ui.controller;

import com.senai.conta_bancaria.aplication.dto.ContaDTO;
import com.senai.conta_bancaria.aplication.dto.ValorDTO;
import com.senai.conta_bancaria.aplication.service.ContaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ContaDTO salvarConta(@RequestBody ContaDTO dto) {
        return contaService.salvarConta(dto);
    }

    @GetMapping
    public List<ContaDTO> listarConta() {
        return contaService.listarConta();
    }

    @GetMapping("/{id}")
    public ContaDTO buscarConta(@PathVariable String id) {
        return contaService.buscarContaPorId(id);
    }

    @PutMapping("/{id}")
    public ContaDTO atualizarConta(@PathVariable String id, @RequestBody ContaDTO dto) {
        return contaService.atualizarConta(id, dto);
    }

    @PutMapping("/{id}")
    public ContaDTO depositar(@PathVariable String id, @RequestBody ValorDTO dto) {
        return contaService.depositar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletarConta(@PathVariable String id) {
        contaService.deletarConta(id);
    }
}
