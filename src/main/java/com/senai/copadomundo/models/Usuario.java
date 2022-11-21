package com.senai.copadomundo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "login", unique = true, length = 80)
    private String email;

    @Column(nullable = false, length = 20)
    private String senha;

    @Column(nullable = false, name = "data_nascimento")
    private LocalDateTime dataNascimento;



}
