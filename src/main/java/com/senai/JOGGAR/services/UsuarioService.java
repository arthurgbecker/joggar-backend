package com.senai.JOGGAR.services;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.JOGGAR.dtos.UsuarioInputDTO;
import com.senai.JOGGAR.dtos.UsuarioOutputDTO;
import com.senai.JOGGAR.entities.Usuario;
import com.senai.JOGGAR.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
    @Autowired
    private UsuarioRepository repository;


    @Transactional
    public UsuarioOutputDTO create(UsuarioInputDTO dto) {
        Usuario usuario = new Usuario(dto);
        var senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        Usuario usuarioCriado = repository.save(usuario);
        return converterEntidadeParaDTO(usuarioCriado);
    }

     public UsuarioOutputDTO converterEntidadeParaDTO(Usuario usuario){
        UsuarioOutputDTO dtoSaida = new UsuarioOutputDTO();
        dtoSaida.setId(usuario.getId());
        dtoSaida.setNome(usuario.getNome());
        dtoSaida.setNascimento(usuario.getNascimento());
        dtoSaida.setGeneroUsuario(usuario.getGeneroUsuario());
        dtoSaida.setEmail(usuario.getEmail());
        dtoSaida.setSenha(usuario.getSenha());
        dtoSaida.setTelefone(usuario.getTelefone());
        dtoSaida.setIsAdmin(usuario.getIsAdmin());
        return dtoSaida;
    }

    public Usuario converterDtoParaEntidade(UsuarioInputDTO dto){
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setNascimento(dto.getNascimento());
        usuario.setGeneroUsuario(dto.getGeneroUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setTelefone(dto.getTelefone());
        usuario.setIsAdmin(dto.getIsAdmin());
        return usuario;
    }

    public UsuarioOutputDTO read(Long id){
        Usuario usuario = repository.findById(id).get();
        return converterEntidadeParaDTO(usuario);
    }

    public List<UsuarioOutputDTO> list(){
        return repository.findAll().stream().map(p->converterEntidadeParaDTO(p)).toList();
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Transactional
    public UsuarioOutputDTO update(UsuarioInputDTO usuario){
        if(usuario.getId() == null){
            usuario.setId(99l);
        }
        if(repository.existsById(usuario.getId())){
            Usuario usuarioAtualizado = repository.save(converterDtoParaEntidade(usuario));
            return converterEntidadeParaDTO(usuarioAtualizado);
        }else{
            return null;
        }
    }


    // public Usuario findByEmail(String email) {
    //     var result = repository.findByEmail(email);

    //     if (result.isPresent()){
    //         return result.get();
    //     } else {
    //         // throw new UserNotFoundException();
    //         return null;
    //     }
    // }

        @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repository.findByEmail(username);
        if(user != null){
           return org.springframework.security.core.userdetails.User.builder()
            .password(user.getSenha())
            .username(user.getEmail())
        .build();
        }else{
            throw new UsernameNotFoundException("");
        }
    }
}
