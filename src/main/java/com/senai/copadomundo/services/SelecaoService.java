package com.senai.copadomundo.services;

import com.senai.copadomundo.models.Selecao;
import com.senai.copadomundo.repositories.JogadoresRepository;
import com.senai.copadomundo.repositories.SelecaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
@AllArgsConstructor
public class SelecaoService {


    private SelecaoRepository selecaoRepository;

    private JogadoresRepository jogadoresRepository;

    public Selecao save(Selecao selecao) {


    return selecaoRepository.save(selecao);
    }

    public List<Selecao> get() {
        return selecaoRepository.findAll();
    }

    public boolean existsBySigla(String sigla) {
        return selecaoRepository.existsBySiglaIgnoreCase(sigla);
    }

    public Selecao getById(String id) {
        return selecaoRepository.findById(id).get();
    }
}