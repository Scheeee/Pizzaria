package com.uniamerica.pizzaria.entity;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    Cliente cliente = new Cliente(1,"Sche", "45-98034-3600");

    Endereco endereco = new Endereco(1,"Avenida tancredo neves","1234", "casa 123", cliente);



    @Test
    void getId() {

        long id = endereco.getId();

        Assert.assertEquals(1L, id, 0);

    }

    @Test
    void getRua() {
        String rua = endereco.getRua();

        Assert.assertEquals("Avenida tancredo neves", rua);
    }

    @Test
    void getNumero() {
        String numero = endereco.getNumero();

        Assert.assertEquals("1234", numero);
    }

    @Test
    void getComplemento() {
        String complemento = endereco.getComplemento();

        Assert.assertEquals("casa 123", complemento);
    }

    @Test
    void setRua() {
        endereco.setRua("rua 1");
        String rua = endereco.getRua();

        Assert.assertEquals("rua 1", rua);

    }


    @Test
    void setNumero() {
        endereco.setNumero("1");

        String num = endereco.getNumero();
        Assert.assertEquals("1", num);
    }

    @Test
    void setComplemento() {
        endereco.setComplemento("casa 1");

        String complemento = endereco.getComplemento();

        Assert.assertEquals("casa 1", complemento);
    }

    @Test
    void setCliente() {
        Cliente cliente1 = new Cliente(2,"Sche", "45-98034-3600");

        endereco.setCliente(cliente1);

        Long id = endereco.getCliente().getId();

        Assert.assertEquals(2, id, 0 );
    }
}