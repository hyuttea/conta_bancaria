package com.senai.conta_bancaria.interface_ui.controller;
import com.senai.conta_bancaria.aplication.dto.ClienteRegistroDTO;
import com.senai.conta_bancaria.aplication.dto.ClienteResponseDTO;
import com.senai.conta_bancaria.aplication.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;


    @PostMapping
    public ClienteResponseDTO registrarCliente(@RequestBody ClienteRegistroDTO dto){
        return service.registrarClienteOuAnexarConta(dto);
    }

}
