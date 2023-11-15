package com.senai.JOGGAR;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.senai.JOGGAR.entities.Usuario;
import com.senai.JOGGAR.repositories.UsuarioRepository;
import com.senai.JOGGAR.services.UsuarioService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void findByEmailNotFound() throws IOException {
        String email = "teste@teste.com";

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        Usuario resultado = usuarioService.findByEmail(email);

        assertNull(resultado);
    } 

    // @Test
    // public void findByEmailValid() throws IOException {
    //     String email = "teste@teste.com";

    //     var usuario = new Usuario();
    //     usuario.setEmail(email);

    //     var optional = Optional.of(usuario);

    //     when(usuarioRepository.findByEmail(email)).thenReturn(optional);

    //     Usuario resultado = UsuarioService.findByEmail(email);

    //     assertNotNull(resultado);
    //     assertEquals(email, resultado.getEmail());
    // }

    // @Test
    // public void saveFlushValid() throws IOException {
    //     String email = "teste@teste.com";

    //     var usuario = new Usuario();
    //     usuario.setEmail(email);
    //     when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

    //     Usuario resultado = UsuarioService.saveAndFlush(usuario);

    //     assertEquals(usuario, resultado);
    // }
}