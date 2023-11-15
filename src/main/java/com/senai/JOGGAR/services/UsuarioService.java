package com.senai.JOGGAR.services;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.JOGGAR.dtos.UsuarioInputDTO;
import com.senai.JOGGAR.entities.Administrador;
import com.senai.JOGGAR.entities.Usuario;
import com.senai.JOGGAR.repositories.AdministradorRepository;
import com.senai.JOGGAR.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AdministradorRepository adminRepository;

    @Transactional
    public Usuario create(UsuarioInputDTO dto, Boolean isAdmin) {
        Usuario usuario = new Usuario(dto);
        if (isAdmin) {
            Usuario usuarioCriado = adminRepository.save((Administrador) usuario);
            return usuarioCriado;
        } else {
            Usuario usuarioCriado = repository.save(usuario);
            return usuarioCriado;
        }
    }

    public Usuario read(Long id) {
        return repository.findById(id).get();
    }

    public List<Usuario> list() {
        List<Usuario> usuarios = (List<Usuario>) repository.findAll();
        return usuarios;
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Usuario update(Usuario usuario) {
        if (repository.existsById(usuario.getId())) {
            return repository.save(usuario);
        } else {
            return null;
        }
    }

    public Usuario findByEmail(String email) {
        var result = repository.findByEmail(email);

        if (result.isPresent()){
            return result.get();
        } else {
            // throw new UserNotFoundException();
            return null;
        }
    }
}
