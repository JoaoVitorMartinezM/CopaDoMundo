package com.senai.copadomundo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private String grupo;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro = LocalDateTime.from(LocalDate.now());

    @OneToMany()
    private List<Jogador> jogadores = new ArrayList<>();
}
