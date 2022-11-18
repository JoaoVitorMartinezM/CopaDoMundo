package com.senai.copadomundo.controllers;

import com.senai.copadomundo.exceptions.SelecaoBadRequest;
import com.senai.copadomundo.exceptions.SelecaoConflict;
import com.senai.copadomundo.models.Selecao;
import com.senai.copadomundo.services.SelecaoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/selecoes", name = "Endpoints Selecao")
@AllArgsConstructor
public class SelecaoController {

    private SelecaoService services;
    private ModelMapper mapper;

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

    @PutMapping(value = "/sigla{sigla}", name = "Put Selecao")
    public ResponseEntity<Selecao> edit(@RequestParam String sigla, @RequestBody Selecao selecao){
        if (!services.existsBySigla(sigla)){
            return ResponseEntity.notFound().build();
        }

        if (selecao.getNome().equals("") || selecao.getSigla().equals("")){
            ResponseEntity.status(400).body(null);
            throw new SelecaoBadRequest("Não é possível gravar uma seleção com nome ou sigla nulos");
        }

        return ResponseEntity.ok(services.edit(sigla, selecao));
    }

    @DeleteMapping(value = "/sigla{sigla}", name = "Delete Selecao")
    public ResponseEntity<String> delete(@RequestParam String sigla){
        if (!services.existsBySigla(sigla)){
            return ResponseEntity.notFound().build();
        }
        services.delete(sigla);

        return ResponseEntity.status(204).body("Seleção excluída do banco de dados com sucesso.");
    }

}
