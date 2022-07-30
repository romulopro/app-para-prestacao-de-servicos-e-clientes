package com.github.romulopro.cliente.repository;

import com.github.romulopro.cliente.entity.Cliente;
import com.github.romulopro.cliente.entity.ServicoPrestado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.net.CacheRequest;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ServicoPrestadoRepositoryTest {

    @Autowired
    private ServicoPrestadoRepository underTest;

    @Autowired
    ClienteRepository clienteRepository;

    @BeforeEach
    void setUp(){
        underTest.deleteAll();
        clienteRepository.deleteAll();
    }

    @Test
    void injecoesDeDependenciaNaoSaoNulos(){
        assertThat(underTest).isNotNull();
        assertThat(clienteRepository).isNotNull();
    }
    @Test
    void dado1ServicoSalvo_SeExistirUmServicoFeitoNoMesParaOCliente_RetornaListaCom1ServicoPrestado() {
        //given
        Cliente cliente  = clienteRepository.save(new Cliente(null, "Renato", "77874264037", null));

        ServicoPrestado servicoprestado = new ServicoPrestado();
        servicoprestado.setCliente(cliente);
        servicoprestado.setDescricao("aa");
        servicoprestado.setValor(BigDecimal.valueOf(50.00));

        servicoprestado.setData(LocalDate.of(2022, 5, 20));

        underTest.save(servicoprestado);

        List<ServicoPrestado> servicoRetornado = underTest.findByNomeClienteAndMes("Renato", 5);
        assertEquals(1, servicoRetornado.size());
        assertThat(cliente.getId()).isEqualTo(servicoRetornado.get(0).getCliente().getId());
    }

    @Test
    void dado2ClientesCom2ServicosPrestadosNoMesmoMesCada_QuandoConsultarServicoDeUmDeles_EntaoRetornaAListaDeServicosDele(){
        Cliente cliente  = clienteRepository.save(new Cliente(null, "Renato", "77874264037", null));
        Cliente cliente2  = clienteRepository.save(new Cliente(null, "Renato2", "44217307004", null));

        ServicoPrestado servicoprestado = new ServicoPrestado();
        servicoprestado.setCliente(cliente);
        servicoprestado.setDescricao("aa");
        servicoprestado.setValor(BigDecimal.valueOf(50.00));
        servicoprestado.setData(LocalDate.of(2022, 5, 20));
        underTest.save(servicoprestado);

        servicoprestado = new ServicoPrestado();
        servicoprestado.setCliente(cliente2);
        servicoprestado.setDescricao("bbbb");
        servicoprestado.setValor(BigDecimal.valueOf(70.00));

        servicoprestado.setData(LocalDate.of(2022, 5, 20));

        underTest.save(servicoprestado);

        servicoprestado = new ServicoPrestado();
        servicoprestado.setCliente(cliente2);
        servicoprestado.setDescricao("ccccc");
        servicoprestado.setValor(BigDecimal.valueOf(90.00));

        servicoprestado.setData(LocalDate.of(2022, 5, 30));


        underTest.save(servicoprestado);

        servicoprestado = new ServicoPrestado();
        servicoprestado.setCliente(cliente);
        servicoprestado.setDescricao("dddddd");
        servicoprestado.setValor(BigDecimal.valueOf(40.00));

        servicoprestado.setData(LocalDate.of(2022, 5, 22));

        underTest.save(servicoprestado);

        List<ServicoPrestado> servicoRetornado = underTest.findByNomeClienteAndMes("Renato2", 5);
        assertEquals(2, servicoRetornado.size());

    }

}