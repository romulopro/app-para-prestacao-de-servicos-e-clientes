package com.github.romulopro.cliente.repository;

import com.github.romulopro.cliente.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository underTest;

    @BeforeEach
    void setUp(){
        underTest.deleteAll();
    }

    @Test
    void seNomeDeUsuarioExistir_EntaoRetornaOUsuario() {
        Usuario user1 = new Usuario();
        user1.setUsername("Sérgio");
        user1.setPassword("1233");
        underTest.save(user1);

        assertThat(underTest.findByUsername("Sérgio")).isNotEmpty();
        assertThat(underTest.findByUsername("Sérgio").get().getPassword()).isEqualTo("1233");
    }

    @Test
    void seNaoExistirUsuario_EntaoRetornaOptionalVazio(){
        assertThat(underTest.findByUsername("Sérgio")).isEmpty();
    }

    @Test
    void seUserNameExistir_EntaoRetornaTrue() {
        Usuario user1 = new Usuario();
        user1.setUsername("Sérgio");
        user1.setPassword("1233");
        underTest.save(user1);

        assertThat(underTest.existsByUsername("Sérgio")).isTrue();

    }

    @Test
    void seUserNameNaoExistir_EntaoRetornaFalse() {
        Usuario user1 = new Usuario();
        user1.setUsername("Sérgio");
        user1.setPassword("1233");
        underTest.save(user1);

        assertThat(underTest.existsByUsername("Renato")).isFalse();
    }
}