package com.senai.copadomundo.repositories;

import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.senai.copadomundo.models.Jogador;
import com.senai.copadomundo.models.Selecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogadoresRepository extends JpaRepository<Jogador, Integer> {

    List<Jogador> findAllBySelecao(Selecao selecao);

    Boolean existsByNome(String nome);

    Boolean existsBySelecaoAndId(Selecao selecao, Integer id);

}
