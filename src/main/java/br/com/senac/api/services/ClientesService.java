package br.com.senac.api.services;

import br.com.senac.api.dtos.ClientesRequestDto;
import br.com.senac.api.entities.Clientes;
import br.com.senac.api.repositories.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository; // a Classe não deve ser chamada, e sim a o objeto


    public List<Clientes> listar() {
        return clientesRepository.findAll(); // ...por isso escreve-se clientesRepository com "c" minúsculo
    }

    public Clientes criar(ClientesRequestDto cliente) {

        Clientes clientesSaida = new Clientes();
        clientesSaida.setNome(cliente.getNome());
        clientesSaida.setDocumento(cliente.getDocumento());
        clientesSaida.setEmail(cliente.getEmail());
        clientesSaida.setDataNascimento(cliente.getDataNascimento());


        return clientesRepository.save(clientesSaida);
    }
}
