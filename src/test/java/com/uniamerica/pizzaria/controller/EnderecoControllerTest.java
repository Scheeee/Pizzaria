package com.uniamerica.pizzaria.controller;


import com.uniamerica.pizzaria.entity.Endereco;
import com.uniamerica.pizzaria.repository.EnderecoRep;
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
class EnderecoControllerTest {

    @MockBean
    EnderecoRep enderecoRep;

    @Autowired
    private  final EnderecoController enderecoController = new EnderecoController();


    @BeforeEach
    void injectFindAll() {
        Endereco endereco = new Endereco(1, "Avenida tancredo neves", "1234", "casa 123");

        List<Endereco> enderecos = new ArrayList<>();

        enderecos.add(endereco);

        Mockito.when(enderecoRep.findAll()).thenReturn(enderecos);
    }

    @Test
    void getAll() {
        var enderecocontroller = enderecoController.getAll();
        List<Endereco> enderecos = enderecocontroller.getBody();
        int valor = enderecos.size();
        System.out.println(valor);
        Assert.assertEquals(1, valor, 0);
    }

    @Test
    void inserir() {
        Endereco endereco = new Endereco(1,"Avenida tancredo neves","1234", "casa 123");

        var endereco1 = enderecoController.inserir(endereco);


        Assert.assertNotNull(endereco1);
        Assert.assertEquals("Endereço cadastrado com sucesso!", endereco1.getBody());
    }

    @Test
    void updateEndereco() {
    }

    @Test
    void delete() {
    }
}