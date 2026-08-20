package br.com.senac.api.controllers;

import br.com.senac.api.dtos.ClientesRequestDto;
import br.com.senac.api.entities.Clientes;
import br.com.senac.api.services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listar() {
        /*
        List<Clientes> retorno = clientesService.listar();

        return ResponseEntity.ok(retorno);
        */

        return ResponseEntity.ok(clientesService.listar());
    }

    @PostMapping("/criar")
    public ResponseEntity<Clientes> criar(@RequestBody ClientesRequestDto cliente) {

        return ResponseEntity.status(201).body(clientesService.criar(cliente));

    }
}