package br.com.senac.api.services;

import br.com.senac.api.dtos.CidadesRequestDto;
import br.com.senac.api.entidades.Cidades;
import br.com.senac.api.repositorios.CidadesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadesService {

    @Autowired
    private CidadesRepositorio cidadesRepositorio;

    public List<Cidades> listarTodos() {
        return cidadesRepositorio.findAll();
    }

    public Cidades criar(CidadesRequestDto cidade) {
        Cidades cidadesPersist = this.cidadesRequestDtosparaCidades(cidade);
        return cidadesRepositorio.save(cidadesPersist);
    }

    public Cidades atualizar(Long id, CidadesRequestDto cidade) {
        if(cidadesRepositorio.existsById(id)) {
            Cidades cidadesPersist = this.cidadesRequestDtosparaCidades(cidade);
            cidadesPersist.setId(id);
            return cidadesRepositorio.save(cidadesPersist);
        }

        throw new RuntimeException("Cidade não encontrada.");
    }

    public void deletar(Long id) {
        if(cidadesRepositorio.existsById(id)) {
            cidadesRepositorio.deleteById(id);
            return;
        }

        throw new RuntimeException("Cidade não encontrada.");
    }

    public Cidades listarPorId(Long id) {
        Optional<Cidades> cidadesRetorno = cidadesRepositorio.findById(id);
        if(cidadesRetorno.isPresent()) {
            return cidadesRetorno.get();
        }

        throw new RuntimeException("Cidade não encontrada.");
    }

    private Cidades cidadesRequestDtosparaCidades (CidadesRequestDto entrada) {
        Cidades saida = new Cidades();
        saida.setNome(entrada.getNome());
        saida.setUf(entrada.getUf());

        return saida;
    }
}