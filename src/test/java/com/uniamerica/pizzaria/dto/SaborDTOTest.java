package com.uniamerica.pizzaria.dto;


import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import java.util.Collections;



class SaborDTOTest {


    SaborDTO sabor = new SaborDTO(1, "Calabresa", "Calabresa");


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
        sabor.setIngredientes("alho");

        Assertions.assertEquals("alho", sabor.getIngredientes());
    }

    @Test
    void testEquals() {
        SaborDTO sabor1 = new SaborDTO(1, "Calabresa", "Calabresa");
        sabor.equals(sabor1);

        Assertions.assertEquals(sabor, sabor1);
    }

    @Test
    void canEqual() {
        SaborDTO sabor1 = new SaborDTO(1, "Calabresa", "Calabresa");
        sabor.canEqual(sabor1);
        Assertions.assertTrue(sabor.canEqual(sabor1));
    }

    @Test
    void testHashCode() {
        SaborDTO sabor1 = new SaborDTO(1, "Calabresa", "Calabresa");
        Assertions.assertEquals(sabor.hashCode(), sabor1.hashCode());
    }

    @Test
    void testToString() {
        SaborDTO sabor1 = new SaborDTO(1, "Calabresa", "Calabresa");

        Assertions.assertEquals(sabor1.toString(), sabor.toString());
    }
    @Test
    void TestNull(){
        SaborDTO saborDTO = new SaborDTO();

        Assertions.assertEquals(0, saborDTO.getId());

    }


}
