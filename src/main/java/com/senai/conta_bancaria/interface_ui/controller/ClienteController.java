package com.senai.conta_bancaria.interface_ui.controller;
import com.senai.conta_bancaria.aplication.dto.ClienteRegistroDTO;
import com.senai.conta_bancaria.aplication.dto.ClienteResponseDTO;
import com.senai.conta_bancaria.aplication.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;


    @PostMapping
    public ResponseEntity<ClienteResponseDTO> reistarCliente(@RequestBody ClienteRegistroDTO dto){
        ClienteResponseDTO novoCliente = service.registrarClienteOuAnexarConta(dto);
        return ResponseEntity.created(
                URI.create("/api/cliente/cpf/"+novoCliente.cpf())).body(novoCliente);

    }

}
