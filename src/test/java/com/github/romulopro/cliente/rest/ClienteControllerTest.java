package com.github.romulopro.cliente.rest;

import com.github.romulopro.cliente.entity.Cliente;
import com.github.romulopro.cliente.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.IsNull.notNullValue;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    ClienteRepository clienteRepository;

    Cliente cliente1 = Cliente.builder()
            .cpf("00840984812")
            .nome("Fulano")
            .build();

    @Test
    @Disabled
    public void addnewCliente() throws Exception {
        // clienteRepository.save(cliente1);
        Mockito.when(clienteRepository.save(cliente1)).thenReturn(cliente1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(cliente1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cpf").value("00840984812"))
                .andExpect(jsonPath("$.nome").value("Fulano"))
                .andExpect(jsonPath("$", notNullValue()));
    }

}
