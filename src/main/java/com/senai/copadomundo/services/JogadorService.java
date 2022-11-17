package com.senai.copadomundo.services;

import com.senai.copadomundo.models.Jogador;
import com.senai.copadomundo.models.Selecao;
import com.senai.copadomundo.repositories.JogadoresRepository;
import com.senai.copadomundo.repositories.SelecaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JogadorService {

    private SelecaoRepository selecaoRepository;

    private JogadoresRepository jogadoresRepository;

    public Jogador save(Jogador jogador, String sigla) {
        jogador.setSelecao(selecaoRepository.findBySiglaIgnoreCase(sigla));
        return jogadoresRepository.save(jogador);
    }
}
