package br.com.senac.api.services;

import br.com.senac.api.dtos.ClientesRequestDto;
import br.com.senac.api.entities.Clientes;
import br.com.senac.api.repositories.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository; // a Classe não deve ser chamada, e sim o objeto


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

    public void deletar(Long id) {
        Clientes cliente = clientesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        clientesRepository.delete(cliente);
    }

    public Clientes listarPorId(Long id){
        Optional<Clientes> clienteResult = clientesRepository.findById(id);

        if (clienteResult.isPresent()) {
            return clienteResult.get();
        }
        throw new RuntimeException("Paciente não Encontrado!");
    }

    public Clientes atualizar(Long id, ClientesRequestDto cliente) {
        Optional<Clientes> clienteResult = clientesRepository.findById(id);

        this.validarCliente(cliente);


        if (clienteResult.isPresent()) {
            Clientes clientePersist = clienteResult.get();
            clientePersist.setNome(cliente.getNome());
            clientePersist.setDocumento(cliente.getDocumento());
            clientePersist.setEmail(cliente.getEmail());
            clientePersist.setDataNascimento(cliente.getDataNascimento());
            clientePersist.setId(id);

            return clientesRepository.save(clientePersist);
        }

        throw new RuntimeException("Paciente não encontrado!");
    }

    private void validarCliente (ClientesRequestDto cliente) {
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new RuntimeException("Campo NOME é obrigatório");

        }

        if (cliente.getDataNascimento() == null )  {
            throw new RuntimeException("Campo DATA DE NASCIMENTO é obrigatório!");

        }
    }
}
