package com.senai.copadomundo.controllers;

import com.senai.copadomundo.exceptions.SelecaoBadRequest;
import com.senai.copadomundo.exceptions.SelecaoConflict;
import com.senai.copadomundo.models.Selecao;
import com.senai.copadomundo.services.SelecaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/selecoes", name = "Endpoints Selecao")
@AllArgsConstructor
public class SelecaoController {

    private SelecaoService services;

    @GetMapping
    public ResponseEntity<List<Selecao>> get(){
        return  ResponseEntity.ok(services.get());
    }

    @GetMapping(value = "/sigla{sigla}")
    public ResponseEntity<Selecao> getById(@RequestParam String sigla){
        if (services.existsBySigla(sigla)){
            return  ResponseEntity.ok(services.getById(sigla));

        }
        return ResponseEntity.notFound().build();

    }



    @PostMapping(value = "/novo", name = "Post Selecao")
    public ResponseEntity<Selecao> save(@RequestBody Selecao selecao){
        if (services.existsBySigla(selecao.getSigla())){
            ResponseEntity.status(409).body(null);
            throw new SelecaoConflict("Seleção já existente no banco de dados");
        }

        if (selecao.getNome().equals("") || selecao.getSigla().equals("") ){
            ResponseEntity.status(400).body(null);
            throw new SelecaoBadRequest("Não é possível gravar uma seleção com nome ou sigla nulos");
        }
        return ResponseEntity.status(201).body(services.save(selecao));
    }
}
