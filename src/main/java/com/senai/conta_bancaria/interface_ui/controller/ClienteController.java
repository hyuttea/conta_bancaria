package com.senai.conta_bancaria.interface_ui.controller;


import com.senai.conta_bancaria.aplication.dto.ClienteDTO;
import com.senai.conta_bancaria.aplication.dto.CriarClienteDTO;
import com.senai.conta_bancaria.aplication.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService cursoService) {
        this.clienteService = cursoService;
    }

    @PostMapping
    public ClienteDTO salvarCliente(@RequestBody CriarClienteDTO dto) {
        return clienteService.salvarCliente(dto);
    }

    @GetMapping
    public List<ClienteDTO> listarCliente() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ClienteDTO buscarCliente(@PathVariable String id) {
        return clienteService.buscarClientePorId(id);
    }

    @PutMapping("/{id}")
    public ClienteDTO atualizarCliente(@PathVariable String id, @RequestBody ClienteDTO dto) {
        return clienteService.atualizarCliente(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable String id) {
        clienteService.deletarCliente(id);
    }
}
