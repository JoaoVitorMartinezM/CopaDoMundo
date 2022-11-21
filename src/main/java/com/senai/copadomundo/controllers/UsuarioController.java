package com.senai.copadomundo.controllers;


import com.senai.copadomundo.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService services;
}
