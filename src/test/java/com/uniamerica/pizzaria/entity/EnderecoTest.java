package com.uniamerica.pizzaria.entity;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnderecoTest {

    Cliente cliente = new Cliente(1,"Sche", "45-98034-3600");

    Endereco endereco = new Endereco(1,"Avenida tancredo neves","1234", "casa 123", cliente);



    @Test
    void getId() {

        long id = endereco.getId();

        Assertions.assertEquals(1L, id, 0);

    }

    @Test
    void getRua() {
        String rua = endereco.getRua();

        Assertions.assertEquals("Avenida tancredo neves", rua);
    }

    @Test
    void getNumero() {
        String numero = endereco.getNumero();

        Assertions.assertEquals("1234", numero);
    }

    @Test
    void getComplemento() {
        String complemento = endereco.getComplemento();

        Assertions.assertEquals("casa 123", complemento);
    }

    @Test
    void setRua() {
        endereco.setRua("rua 1");
        String rua = endereco.getRua();

        Assertions.assertEquals("rua 1", rua);

    }


    @Test
    void setNumero() {
        endereco.setNumero("1");

        String num = endereco.getNumero();
        Assertions.assertEquals("1", num);
    }

    @Test
    void setComplemento() {
        endereco.setComplemento("casa 1");

        String complemento = endereco.getComplemento();

        Assertions.assertEquals("casa 1", complemento);
    }

    @Test
    void setCliente() {
        Cliente cliente1 = new Cliente(2,"Sche", "45-98034-3600");

        endereco.setCliente(cliente1);

        long id = endereco.getCliente().getId();

        Assertions.assertEquals(2, id, 0);
    }
}