package com.github.romulopro.cliente.service;

import com.github.romulopro.cliente.entity.Usuario;
import com.github.romulopro.cliente.exception.UsuarioCadastradoException;
import com.github.romulopro.cliente.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
//camada de servico � a que deve conter as regras de neg�cio
@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService{

    
    private final UsuarioRepository usuarioRepository;
    
    public Usuario salvar(Usuario usuario){
        boolean exists  = usuarioRepository.existsByUsername(usuario.getUsername());
        if(exists){
            throw new UsuarioCadastradoException(usuario.getUsername());
        }
        log.info("Salvando usuario {}", usuario.getUsername());
        return usuarioRepository.save(usuario);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{


        Usuario usuario = usuarioRepository
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado"));

        return org.springframework.security.core.userdetails.User.builder()
            .username(usuario.getUsername())
            .password(usuario.getPassword())
            .roles("USER")
            .build();
    }
}
