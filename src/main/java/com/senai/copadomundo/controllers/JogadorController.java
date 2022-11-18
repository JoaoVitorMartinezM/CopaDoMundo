package com.senai.copadomundo.controllers;

import com.senai.copadomundo.models.Jogador;
import com.senai.copadomundo.services.JogadorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/selecoes/{sigla}/jogadores",name = "Endpoints de Jogadores")
public class JogadorController {

    private JogadorService service;
    private ModelMapper mapper;

    @PostMapping(name = "Post Jogadores")
    public ResponseEntity<Jogador> save( @PathVariable("sigla") String sigla, @RequestBody Jogador jogador){

        return ResponseEntity.ok(service.save(jogador, sigla));

    }
}
