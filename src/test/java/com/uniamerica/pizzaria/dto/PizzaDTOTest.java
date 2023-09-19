package com.uniamerica.pizzaria.dto;

import com.uniamerica.pizzaria.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import java.util.Collections;



class PizzaDTOTest {
    Atendente atendente = new Atendente(1, "Sche");
    Cliente cliente = new Cliente(1,"Sche", "45-98034-3600");

    Sabor sabor = new Sabor(1, "Calabresa", Collections.singletonList("Calabresa"));
    BigDecimal valor = new BigDecimal("25");


    Pedido pedido = new Pedido(1, atendente, cliente, false, "retirar cebola",valor, true);
    PizzaDTO pizza = new PizzaDTO(1, Tamanho.P, Collections.singletonList(sabor), valor, pedido);
    @Test
    void getTamanhoandSetTamanho() {
        pizza.setTamanho(Tamanho.GG);

        Assertions.assertEquals(Tamanho.GG, pizza.getTamanho());
    }

    @Test
    void getSaboresandSetSabores() {
        Sabor sabor1 = new Sabor(1, "Strogonoff", Collections.singletonList("frango"));
        pizza.setSabores(Collections.singletonList(sabor1));
        Assertions.assertEquals(sabor1, pizza.getSabores().get(0));
    }

    @Test
    void getPedidoandSetPedido() {



    }





    @Test
    void getValorUnitandSetValorUnit() {

        pizza.setValorUnit(new BigDecimal(100));

        Assertions.assertEquals(new BigDecimal(100), pizza.getValorUnit());
    }

    @Test
    void setIdAndGetId() {
        pizza.setId(2);
        Assertions.assertEquals(2, pizza.getId());
    }

    @Test
    void testEquals() {
        PizzaDTO pizza2 = new PizzaDTO(1, Tamanho.P, Collections.singletonList(sabor), valor, pedido);
        Assertions.assertEquals(pizza, pizza2);
    }

    @Test
    void canEqual() {
        PizzaDTO pizza2 = new PizzaDTO(1, Tamanho.P, Collections.singletonList(sabor), valor, pedido);
        Assertions.assertTrue(pizza.canEqual(pizza2));
    }




}