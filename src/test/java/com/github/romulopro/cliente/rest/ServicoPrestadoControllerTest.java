package com.github.romulopro.cliente.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.romulopro.cliente.entity.Cliente;
import com.github.romulopro.cliente.repository.ServicoPrestadoRepository;
import com.github.romulopro.cliente.rest.dto.ServicoPrestadoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(ServicoPrestadoController.class)

public class ServicoPrestadoControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    
    @MockBean
    ServicoPrestadoRepository servicoPrestadoRepository;
    Cliente cliente1 = Cliente.builder()
			.cpf("95891985039")
			.nome("Fulano")
			.build();
    ServicoPrestadoDTO RECORD_1 = new ServicoPrestadoDTO();
    

}
