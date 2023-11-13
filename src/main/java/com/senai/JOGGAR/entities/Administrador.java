package com.senai.JOGGAR.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode(callSuper=true)
@Data @AllArgsConstructor @NoArgsConstructor

public class Administrador extends Usuario {
    private Boolean isAdmin;
}