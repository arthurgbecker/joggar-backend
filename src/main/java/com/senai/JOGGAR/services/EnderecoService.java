package com.senai.JOGGAR.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.JOGGAR.entities.Endereco;
import com.senai.JOGGAR.repositories.EnderecoRepository;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;

    public Endereco create(Endereco endereco){
        Endereco enderecoCriado = repository.save(endereco);
        return enderecoCriado;
    }

    public Endereco read(Long id){
        return repository.findById(id).get();
    }

    public List<Endereco> list(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Endereco update(Endereco endereco){
        if(repository.existsById(endereco.getId())){
            return repository.save(endereco);
        }else{
            return null;
        }
    }
}
