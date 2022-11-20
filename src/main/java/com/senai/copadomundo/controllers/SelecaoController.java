package com.senai.copadomundo.controllers;

import com.senai.copadomundo.dto.SelecaoRequest;
import com.senai.copadomundo.dto.SelecaoResponse;
import com.senai.copadomundo.exceptions.SelecaoBadRequest;
import com.senai.copadomundo.exceptions.SelecaoConflict;
import com.senai.copadomundo.models.Selecao;
import com.senai.copadomundo.services.SelecaoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/selecoes", name = "Endpoints Selecao")
@AllArgsConstructor
public class SelecaoController {

    private SelecaoService services;
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<SelecaoResponse>> get(){
        List<Selecao> lista = services.get();
        List<SelecaoResponse> listaResponseMapeada = lista.stream().map(selecao -> mapper.map(selecao, SelecaoResponse.class)).collect(Collectors.toList());

        return  ResponseEntity.ok(listaResponseMapeada);
    }

    @GetMapping(value = "/sigla{sigla}")
    public ResponseEntity<SelecaoResponse> getById(@RequestParam String sigla){
        if (services.existsBySigla(sigla)){
            Selecao selecao = services.getById(sigla);
            SelecaoResponse response = mapper.map(selecao, SelecaoResponse.class);
            return  ResponseEntity.ok(response);

        }
        return ResponseEntity.notFound().build();

    }



    @PostMapping(value = "/novo", name = "Post Selecao")
    public ResponseEntity<SelecaoResponse> save(@RequestBody SelecaoRequest request){
        Selecao selecao = mapper.map(request, Selecao.class);
        if (services.existsBySigla(selecao.getSigla())){
            ResponseEntity.status(409).body(null);
            throw new SelecaoConflict("Seleção já existente no banco de dados");
        }

        if (selecao.getNome().equals("") || selecao.getSigla().equals("") ){
            ResponseEntity.status(400).body(null);
            throw new SelecaoBadRequest("Não é possível gravar uma seleção com nome ou sigla nulos");
        }
        Selecao selecaoBD = services.save(selecao);

        SelecaoResponse response = mapper.map(selecaoBD, SelecaoResponse.class);

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping(value = "/sigla{sigla}", name = "Put Selecao")
    public ResponseEntity<SelecaoResponse> edit(@RequestParam String sigla, @RequestBody SelecaoRequest request){
        Selecao selecao = mapper.map(request, Selecao.class);
        if (!services.existsBySigla(sigla)){
            return ResponseEntity.notFound().build();
        }

        if (selecao.getNome().equals("") || sigla.equals("")){
            ResponseEntity.status(400).body(null);
            throw new SelecaoBadRequest("Não é possível gravar uma seleção com nome ou sigla nulos");
        }
        Selecao selecaoEditadaBD = services.edit(sigla, selecao);

        SelecaoResponse response = mapper.map(selecaoEditadaBD, SelecaoResponse.class);

        return ResponseEntity.ok(response);
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
