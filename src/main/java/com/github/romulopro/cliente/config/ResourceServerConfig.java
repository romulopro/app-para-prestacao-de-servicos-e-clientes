package com.github.romulopro.cliente.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
    
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        //usuarios sao divididos em roles
        // roles são perfil de usuario, permite criar diversos perfis de usuario
        httpSecurity
        .authorizeRequests()
                .antMatchers("/api/usuarios**", "/oauth/token**", "/h2-console**").permitAll();

        httpSecurity.authorizeRequests().antMatchers("/api/clientes/**", "/api/servicos-prestados/**").authenticated()
        .anyRequest().denyAll();

    }
}
