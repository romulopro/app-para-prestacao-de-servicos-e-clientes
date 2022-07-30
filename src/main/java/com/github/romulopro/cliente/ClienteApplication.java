package com.github.romulopro.cliente;


import com.github.romulopro.cliente.entity.Cliente;
import com.github.romulopro.cliente.entity.Usuario;
import com.github.romulopro.cliente.repository.ClienteRepository;
import com.github.romulopro.cliente.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ClienteApplication {

	 @Bean
	 public CommandLineRunner run(@Autowired UsuarioRepository usuarioRepository){
	 	return args -> {
	 		Usuario user = new Usuario();
					user.setUsername("a");
					user.setPassword("1234");
			usuarioRepository.save(user);
	 	};
	 }

	public static void main(String[] args) {
		SpringApplication.run(ClienteApplication.class, args);
	}

}
