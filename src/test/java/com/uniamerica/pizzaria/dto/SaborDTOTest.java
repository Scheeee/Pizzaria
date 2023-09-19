package com.uniamerica.pizzaria.dto;


import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import java.util.Collections;



class SaborDTOTest {


    SaborDTO sabor = new SaborDTO(1, "Calabresa", Collections.singletonList("Calabresa"));


    @Test
    void setIdandGetId() {
        sabor.setId(2);
        Assertions.assertEquals(2, sabor.getId());
    }

    @Test
    void setNomeandGetNome() {
        sabor.setNome("calabresa com alho");
        Assertions.assertEquals("calabresa com alho", sabor.getNome());
    }

    @Test
    void setIngredientesandGet() {
        sabor.setIngredientes(Collections.singletonList("alho"));

        Assertions.assertEquals("alho", sabor.getIngredientes().get(0));
    }

    @Test
    void testEquals() {
        SaborDTO sabor1 = new SaborDTO(1, "Calabresa", Collections.singletonList("Calabresa"));
        sabor.equals(sabor1);

        Assertions.assertEquals(sabor, sabor1);
    }

    @Test
    void canEqual() {
        SaborDTO sabor1 = new SaborDTO(1, "Calabresa", Collections.singletonList("Calabresa"));
        sabor.canEqual(sabor1);
        Assertions.assertEquals(true, sabor.canEqual(sabor1));
    }

    @Test
    void testHashCode() {
        sabor.hashCode();
    }

    @Test
    void testToString() {
        sabor.toString();
        Assertions.assertEquals("SaborDTO(id=0, nome=null, ingredientes=null)", sabor.toString());
    }


}