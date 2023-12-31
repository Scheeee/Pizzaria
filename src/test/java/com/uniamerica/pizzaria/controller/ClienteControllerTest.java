package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.dto.ClienteDTO;
import com.uniamerica.pizzaria.entity.Cliente;
import com.uniamerica.pizzaria.entity.Endereco;
import com.uniamerica.pizzaria.repository.ClienteRep;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest


class ClienteControllerTest {

    @MockBean
    ClienteRep clienteRep;

    @Autowired
    private final ClienteController clienteController = new ClienteController();


    @BeforeEach
    void injectFindByAll(){
        Cliente cliente = new Cliente("Sche", "45-98034-3600");

        List<Cliente> clientes = new ArrayList<>();

        clientes.add(cliente);
        Mockito.when(clienteRep.findAll()).thenReturn(clientes);

    }
    @BeforeEach
    void injectUpdate(){
        Endereco endereco = new Endereco(1,"Avenida tancredo neves","1234", "casa 123");
        Cliente cliente = new Cliente(1,"Sche", "45-98034-3600", endereco);


        Mockito.when(clienteRep.getReferenceById(1L)).thenReturn(cliente);

    }

    @Test
    void getAll() {
        var clientecontroler = clienteController.findAll();
        List<Cliente> clientes = clientecontroler.getBody();
        int valor = 0;
        if (clientes != null) {
            valor = clientes.size();
        }
        System.out.println(valor);
        Assertions.assertEquals(1, valor, 0);
    }

    @Test
    void inserir() {
        ClienteDTO cliente = new ClienteDTO(1,"Sche", "45-98034-3600");
        var cliente1 = clienteController.inserir(cliente);


        Assertions.assertNotNull(cliente1);
        Assertions.assertEquals(HttpStatusCode.valueOf(200), cliente1.getStatusCode());

    }
    @Test
    void inserirErro() {

        var cliente1 = clienteController.inserir(new ClienteDTO());


        Assertions.assertNotNull(cliente1);
        Assertions.assertEquals(HttpStatusCode.valueOf(500), cliente1.getStatusCode());

    }
    @Test
    void updateCliente() {
        Endereco endereco = new Endereco(1,"Avenida tancredo neves","1234", "casa 123");
        ClienteDTO cliente = new ClienteDTO(1,"Gabriele", "45-98034-3600", endereco);
        var cliente1 = clienteController.updateCliente(1L, cliente);


        Assertions.assertNotNull(cliente1);
        Assertions.assertEquals(null, cliente1.getBody());
    }

    @Test
    void delete() {
        inserir();

        var delete = clienteController.delete(1L);

        Assertions.assertNotNull(delete);
        Assertions.assertEquals(null, delete.getBody());
    }
}
