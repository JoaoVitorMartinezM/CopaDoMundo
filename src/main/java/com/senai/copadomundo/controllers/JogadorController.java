package com.senai.copadomundo.controllers;

import com.senai.copadomundo.dto.JogadorRequest;
import com.senai.copadomundo.dto.JogadorResponse;
import com.senai.copadomundo.models.Jogador;
import com.senai.copadomundo.services.JogadorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/selecoes/{sigla}/jogadores",name = "Endpoints de Jogadores")
public class JogadorController {

    private JogadorService service;
    private ModelMapper mapper;

    @PostMapping(name = "Post Jogadores")
    public ResponseEntity<JogadorResponse> save( @PathVariable("sigla") String sigla, @RequestBody JogadorRequest request){
        Jogador jogador = mapper.map(request, Jogador.class);

        if (jogador.getNome() == null || jogador.getPosicao() == null){
            return ResponseEntity.badRequest().body(null);
        }

        Jogador jogadorBD = service.save(jogador, sigla);

        if (jogadorBD == null){
            return ResponseEntity.status(409).body(null);
        }


        JogadorResponse response = mapper.map(jogadorBD, JogadorResponse.class);



        return ResponseEntity.status(201).body(response);

    }

    @GetMapping(name = "Get Jogadores")
    public ResponseEntity<List<Jogador>> get(){
        List<Jogador> jogadores = service.get();
        return ResponseEntity.ok(jogadores);
    }

    @GetMapping(value = "/",name = "Get Jogadores por Seleção")
    public ResponseEntity<List<Jogador>> getBySelecao(@PathVariable String sigla){
        List<Jogador> jogadores = service.get(sigla);
        return ResponseEntity.ok(jogadores);
    }
}
