package com.senai.JOGGAR.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class LoginInputDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
