package br.com.senac.api.services;

import br.com.senac.api.dtos.ClienteEnderecoRequestDto;
import br.com.senac.api.dtos.ClientesRequestDto;
import br.com.senac.api.entidades.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesEnderecosService {

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private EnderecosService enderecosService;

    public Clientes criarClienteEnderecos(ClientesRequestDto cliente) {
        Clientes clienteRetorno = this.clientesService.criar(cliente);

        if(cliente.getEnderecos() != null && !cliente.getEnderecos().isEmpty()) {
            for(ClienteEnderecoRequestDto end : cliente.getEnderecos()) {
                end.setClienteId(clienteRetorno.getId());
                this.enderecosService.criar(end);
            }

            return this.clientesService.listarPorId(clienteRetorno.getId());
        }

        return clienteRetorno;
    }
}
