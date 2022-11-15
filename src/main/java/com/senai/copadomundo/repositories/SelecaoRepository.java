package com.senai.copadomundo.repositories;

import com.senai.copadomundo.models.Selecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelecaoRepository extends JpaRepository<Selecao, String> {

    Boolean existsBySiglaIgnoreCase(String sigla);
}
