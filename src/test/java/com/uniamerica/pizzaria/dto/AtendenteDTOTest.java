package com.uniamerica.pizzaria.dto;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



class AtendenteDTOTest {

    AtendenteDTO atendente = new AtendenteDTO(1L, "Sche");



    @Test
    void setNomeandgetNome() {
        atendente.setNome("Scheligan");

        Assertions.assertEquals("Scheligan", atendente.getNome());
    }

    @Test
    void setIdandGetId() {
        atendente.setId(2);

        Assertions.assertEquals(2, atendente.getId());
    }



    @Test
    void testEquals() {

        AtendenteDTO atendente1 = new AtendenteDTO(1L, "Sche");

        Assertions.assertEquals(atendente, atendente1);
    }

    @Test
    void canEqual() {
        AtendenteDTO atendente1 = new AtendenteDTO(1L, "Sche");

        Assertions.assertTrue(atendente1.canEqual(atendente));


    }

    @Test
    void testHashCode() {
        AtendenteDTO atendente1 = new AtendenteDTO(1L, "Sche");
        AtendenteDTO atendente2 = new AtendenteDTO(1L, "Sche");


        Assertions.assertEquals(atendente1.hashCode(), atendente2.hashCode());;

    }

    @Test
    void testToString() {
        AtendenteDTO atendente2 = new AtendenteDTO(1L, "Sche");
        Assertions.assertEquals(atendente2.toString(), atendente.toString());


    }
    @Test
    void TestNull(){
        AtendenteDTO atendenteDTO = new AtendenteDTO();

        Assertions.assertEquals(0, atendenteDTO.getId());

    }



}