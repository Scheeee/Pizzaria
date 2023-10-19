package com.uniamerica.pizzaria.entity;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class PizzaTest {
    Sabor sabor = new Sabor(1, "Calabresa", "Calabresa");
    BigDecimal valor = new BigDecimal("25");
    Pizza pizza = new Pizza(1, Tamanho.P, Collections.singletonList(sabor), valor);

    @Test
    void getId() {
        long id = pizza.getId();
        Assertions.assertEquals(1L, id, 0);
    }

    @Test
    void getTamanho() {
        Tamanho tam = pizza.getTamanho();

        Assertions.assertEquals(Tamanho.P, tam);
    }

    @Test
    void getSabores() {
        Sabor sabor1= pizza.getSabores().get(0);

        long id = sabor1.getId();

        Assertions.assertEquals(1L, id, 0);
    }

    @Test
    void getValorUnit() {
        BigDecimal valorUnit = pizza.getValorUnit();

        Assertions.assertEquals(valor, valorUnit);
    }

    @Test
    void setTamanho() {
        pizza.setTamanho(Tamanho.M);
        Tamanho tam = pizza.getTamanho();

        Assertions.assertEquals(Tamanho.M, tam);

    }

    @Test
    void setSabores() {
        List<Sabor> list = new ArrayList<>();

        Sabor saborNovo = new Sabor(2, "Calabresa", "Calabresa");

        list.add(saborNovo);
        pizza.setSabores(list);
        Sabor sabor1= pizza.getSabores().get(0);

        long id = sabor1.getId();

        Assertions.assertEquals(2L, id, 0);
    }

    @Test
    void setValorUnit() {
        pizza.setValorUnit(new BigDecimal(20));
        BigDecimal valorUnit = pizza.getValorUnit();

        Assertions.assertEquals(new BigDecimal(20), valorUnit);

    }
}
