package com.uniamerica.pizzaria.entity;

import com.uniamerica.pizzaria.controller.AtendenteController;
import com.uniamerica.pizzaria.controller.PedidoController;
import com.uniamerica.pizzaria.repository.AtendenteRep;
import jakarta.validation.constraints.AssertTrue;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AtendenteTest {


    @MockBean
    AtendenteRep atendenteRep;

    @Autowired
    private final PedidoController pedidoController = new PedidoController();

    @BeforeClass
    void injectGetId(){
        Optional<Atendente> atendente = Optional.of(new Atendente(1L,"Sche"));
        Mockito.when(atendenteRep.findById(1L)).thenReturn(atendente);
    }

    @Test
    void getId() {
        Atendente atendente = new Atendente(1, "Sche");


       long id =  atendente.getId();

        Assert.assertEquals(1L, id, 0);

    }

    @Test
    void getNome() {
        Atendente atendente = new Atendente(1, "Sche");

        String nome = atendente.getNome();

        Assert.assertEquals("Sche", nome);
    }

    @Test
    void setNome() {
        Atendente atendente = new Atendente(1, "Sche");

        atendente.setNome("Gabriele");

        String nome = atendente.getNome();
        Assert.assertEquals("Gabriele", nome);
    }
}