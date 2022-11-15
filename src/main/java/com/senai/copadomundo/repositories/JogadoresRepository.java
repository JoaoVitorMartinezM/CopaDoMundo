package com.senai.copadomundo.repositories;

import com.senai.copadomundo.models.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadoresRepository extends JpaRepository<Jogador, Integer> {
}
