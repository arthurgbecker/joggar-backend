// package com.senai.JOGGAR.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.senai.JOGGAR.dtos.LoginInputDTO;
// import com.senai.JOGGAR.dtos.TokenDTO;
// import com.senai.JOGGAR.entities.Usuario;
// import com.senai.JOGGAR.services.TokenService;

// import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
//             dto.getLogin(),
//             dto.getPassword()
//         );
//         var authentication = manager.authenticate(login);
//         var user = (Usuario) authentication.getPrincipal();
//         String token = service.generateToken(usuario);
//         return new ResponseEntity<>(new TokenDTO(token), HttpStatus.CREATED);
//     }
// }
