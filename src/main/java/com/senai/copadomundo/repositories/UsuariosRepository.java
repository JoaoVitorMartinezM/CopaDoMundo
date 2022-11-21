package com.senai.copadomundo.repositories;

import com.senai.copadomundo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
}
