package com.senai.copadomundo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "selecoes")
public class Selecao {

    @Id
    @Column(name = "sigla", length = 3)
    private String sigla;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String grupo;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro = LocalDateTime.now();


}
