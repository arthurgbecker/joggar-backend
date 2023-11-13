package com.senai.JOGGAR.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.JOGGAR.entities.Preferencia;
import com.senai.JOGGAR.repositories.PreferenciaRepository;

@Service
public class PreferenciaService {
    @Autowired
    private PreferenciaRepository repository;

    @Transactional
    public Preferencia create(Preferencia preferencia){
        Preferencia preferenciaCriado = repository.save(preferencia);
        return preferenciaCriado;
    }

    public Preferencia read(Long id){
        return repository.findById(id).get();
    }

    public List<Preferencia> list(){
        List<Preferencia> preferencias = (List<Preferencia>) repository.findAll();
        return preferencias;
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Transactional
    public Preferencia update(Preferencia preferencia){
        if(repository.existsById(preferencia.getId())){
            return repository.save(preferencia);
        }else{
            return null;
        }
    }
}