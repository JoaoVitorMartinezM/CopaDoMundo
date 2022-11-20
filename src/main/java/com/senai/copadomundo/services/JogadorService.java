package com.senai.copadomundo.services;

import com.senai.copadomundo.models.Jogador;
import com.senai.copadomundo.models.Selecao;
import com.senai.copadomundo.repositories.JogadoresRepository;
import com.senai.copadomundo.repositories.SelecaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JogadorService {

    private SelecaoRepository selecaoRepository;

    private JogadoresRepository jogadoresRepository;

    public Jogador save(Jogador jogador, String sigla) {
        jogador.setSelecao(selecaoRepository.findBySiglaIgnoreCase(sigla));

        if (jogadoresRepository.existsByNome(jogador.getNome())){
            return null;
        }

        return jogadoresRepository.save(jogador);
    }

    public List<Jogador> get() {
        return jogadoresRepository.findAll();
    }

    public List<Jogador> get(String sigla) {
        Selecao selecao = selecaoRepository.findBySiglaIgnoreCase(sigla);
        return jogadoresRepository.findAllBySelecao(selecao);
    }

    public boolean delete(Integer id, String sigla) {
        Optional<Selecao> selecao = selecaoRepository.findById(sigla);
        if(jogadoresRepository.existsBySelecaoAndId(selecao.get(), id)){
            jogadoresRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
