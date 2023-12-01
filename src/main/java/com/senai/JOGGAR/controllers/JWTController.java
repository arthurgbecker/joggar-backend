// package com.senai.JOGGAR.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.web.bind.annotation.*;

// import com.senai.JOGGAR.dtos.LoginInputDTO;
// import com.senai.JOGGAR.dtos.TokenDTO;
// import com.senai.JOGGAR.services.TokenService;

// import jakarta.validation.Valid;

// @RestController
// @RequestMapping("login")
// public class JWTController {
//     @Autowired
//     private AuthenticationManager manager;

//     @Autowired
//     private TokenService service;

//     @PostMapping
//     public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginInputDTO dto) {
//         var login = new UsernamePasswordAuthenticationToken(
//             dto.getEmail(),
//             dto.getSenha()
//         );
//         var authentication = manager.authenticate(login);
//         var usuario = (UserDetails) authentication.getPrincipal();
//         String token = service.generateToken(usuario);
//         return new ResponseEntity<>(new TokenDTO(token), HttpStatus.CREATED);
//     }
// }
