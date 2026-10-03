package br.com.senac.api.controllers;

import br.com.senac.api.dtos.ClientesRequestDto;
import br.com.senac.api.entities.Clientes;
import br.com.senac.api.services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        try {
            return ResponseEntity.status(201).body(clientesService.criar(cliente));

        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Clientes> atualizar(@PathVariable Long id,
                                              @RequestBody ClientesRequestDto cliente) {
        return ResponseEntity.ok(clientesService.atualizar(id, cliente));

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        try{
            clientesService.deletar(id);
            return ResponseEntity.noContent().build();

        } catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}