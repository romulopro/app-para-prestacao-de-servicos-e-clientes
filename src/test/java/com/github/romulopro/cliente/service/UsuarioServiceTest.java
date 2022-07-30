package com.github.romulopro.cliente.service;

import com.github.romulopro.cliente.entity.Usuario;
import com.github.romulopro.cliente.exception.UsuarioCadastradoException;
import com.github.romulopro.cliente.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    UsuarioService underTest;

    @BeforeEach
    void setUp() {
        underTest = new UsuarioService(usuarioRepository);
    }

    @Test
    void SeUsuarioNaoExistirNoBD_EntaoSalvar() {

        given(usuarioRepository.existsByUsername(anyString())).willReturn(false);
        Usuario user1 = new Usuario();
        user1.setUsername("Sérgio");
        user1.setPassword("1233");

        underTest.salvar(user1);

        ArgumentCaptor<Usuario> usuarioArgumentCaptor = ArgumentCaptor.forClass(Usuario.class);
        verify(usuarioRepository).save(usuarioArgumentCaptor.capture());

        Usuario capturadoUsuario = usuarioArgumentCaptor.getValue();

        assertThat(user1).isEqualTo(capturadoUsuario);
    }

    @Test
    void seUsuarioJaExistirAoSalvar_EntaoLancaUsuarioCadastradoException(){
        given(usuarioRepository.existsByUsername(anyString())).willReturn(true);
        Usuario user1 = new Usuario();
        user1.setUsername("Sérgio");
        user1.setPassword("1233");

        assertThatThrownBy(() ->underTest.salvar(user1))
                .isInstanceOf(UsuarioCadastradoException.class)
                .hasMessageContaining("Usuario já cadastrado para o login " + user1.getUsername());

    }


}