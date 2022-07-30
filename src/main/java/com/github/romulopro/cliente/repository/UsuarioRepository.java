package com.github.romulopro.cliente.repository;

import java.util.Optional;

import com.github.romulopro.cliente.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
}
