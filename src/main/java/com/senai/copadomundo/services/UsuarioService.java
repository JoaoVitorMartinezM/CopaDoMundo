package com.senai.copadomundo.services;

import com.senai.copadomundo.repositories.UsuariosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuariosRepository usuariosRepository;
}
