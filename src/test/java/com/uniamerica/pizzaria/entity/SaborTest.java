package com.uniamerica.pizzaria.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



class SaborTest {

    Sabor sabor = new Sabor(1, "Calabresa", Collections.singletonList("Calabresa"));

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
        String ingrediente = sabor.getIngredientes().get(0);

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
        List<String> ing = new ArrayList<>();

        ing.add("alho");
        sabor.setIngredientes(ing);

        String ingrediente = sabor.getIngredientes().get(0);

        Assertions.assertEquals("alho", ingrediente);
    }
}