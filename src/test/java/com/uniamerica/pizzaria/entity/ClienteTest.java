package com.uniamerica.pizzaria.entity;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {


        Endereco endereco = new Endereco(1,"Avenida tancredo neves","1234", "casa 123");
        Cliente cliente = new Cliente(1,"Sche", "45-98034-3600", endereco);



    @Test
    void getId() {


        long id =  cliente.getId();

        Assert.assertEquals(1L, id, 0);
    }

    @Test
    void getNome() {


        String nome =  cliente.getNome();

        Assert.assertEquals("Sche", nome);

    }

    @Test
    void getTelefone() {


        String tel =  cliente.getTelefone();

        Assert.assertEquals("45-98034-3600", tel);

    }

    @Test
    void getEndereco() {


        long id =  cliente.getEndereco().getId();

        Assert.assertEquals(1L, id, 0);
    }

    @Test
    void setNome() {


        cliente.setNome("Gabriele");

        String nome = cliente.getNome();
        Assert.assertEquals("Gabriele", nome);
    }

    @Test
    void setTelefone() {


        cliente.setTelefone("45-99999-3600");
        String tel = cliente.getTelefone();

        Assert.assertEquals("45-99999-3600", tel);
    }

    @Test
    void setEndereco() {

        Endereco endereco1 = new Endereco(2,"Avenida tancredo neves","1234", "casa 123");

        cliente.setEndereco(endereco1);
        long id =  cliente.getEndereco().getId();

        Assert.assertEquals(2L, id, 0);
    }
}
