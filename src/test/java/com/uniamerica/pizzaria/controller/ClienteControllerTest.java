package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.entity.Atendente;
import com.uniamerica.pizzaria.entity.Cliente;
import com.uniamerica.pizzaria.entity.Endereco;
import com.uniamerica.pizzaria.repository.ClienteRep;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
        Endereco endereco = new Endereco(1,"Avenida tancredo neves","1234", "casa 123");
        Cliente cliente = new Cliente(1,"Sche", "45-98034-3600", endereco);

        List<Cliente> clientes = new ArrayList<>();

        clientes.add(cliente);
        Mockito.when(clienteRep.findAll()).thenReturn(clientes);

    }

    @Test
    void getAll() {
        var clientecontroler = clienteController.findAll();
        List<Cliente> clientes = (List<Cliente>) clientecontroler.getBody();
        int valor = clientes.size();
        System.out.println(valor);
        Assert.assertEquals(1, valor, 0);
    }

    @Test
    void inserir() {
        Endereco endereco = new Endereco(1,"Avenida tancredo neves","1234", "casa 123");
        Cliente cliente = new Cliente(1,"Sche", "45-98034-3600", endereco);
        var cliente1 = clienteController.inserir(cliente);


        Assert.assertNotNull(cliente1);
        Assert.assertEquals("Cliente cadastrado(a) com sucesso!", cliente1.getBody());

    }

    @Test
    void updateCliente() {
    }

    @Test
    void delete() {
    }
}