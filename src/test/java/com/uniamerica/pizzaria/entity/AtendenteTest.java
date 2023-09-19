package com.uniamerica.pizzaria.entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AtendenteTest {


    @Test
    void getId() {
        Atendente atendente = new Atendente(1, "Sche");


       long id =  atendente.getId();

        Assertions.assertEquals(1L, id, 0);

    }

    @Test
    void getNome() {
        Atendente atendente = new Atendente(1, "Sche");

        String nome = atendente.getNome();

        Assertions.assertEquals("Sche", nome);
    }

    @Test
    void setNome() {
        Atendente atendente = new Atendente(1, "Sche");

        atendente.setNome("Gabriele");

        String nome = atendente.getNome();
        Assertions.assertEquals("Gabriele", nome);
    }

}