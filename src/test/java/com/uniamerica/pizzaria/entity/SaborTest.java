package com.uniamerica.pizzaria.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



class SaborTest {

    Sabor sabor = new Sabor(1, "Calabresa", "Calabresa");

    @Test
    void getId() {
        long id = sabor.getId();

        Assertions.assertEquals(1L, id, 0);
    }

    @Test
    void getNome() {

        String nome = sabor.getNome();

        Assertions.assertEquals("Calabresa", nome);
    }

    @Test
    void getIngredientes() {
        String ingrediente = sabor.getIngredientes();

        Assertions.assertEquals("Calabresa", ingrediente);
    }

    @Test
    void setNome() {
        sabor.setNome("Calabresa com alho");

        String nome = sabor.getNome();

        Assertions.assertEquals("Calabresa com alho", nome);
    }

    @Test
    void setIngredientes() {



        sabor.setIngredientes("alho");

        String ingrediente = sabor.getIngredientes();

        Assertions.assertEquals("alho", ingrediente);
    }
}
