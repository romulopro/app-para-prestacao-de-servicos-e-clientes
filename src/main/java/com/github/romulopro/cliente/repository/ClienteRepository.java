package com.github.romulopro.cliente.repository;

import com.github.romulopro.cliente.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    
}
