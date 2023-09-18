package com.uniamerica.pizzaria.controller;
import com.uniamerica.pizzaria.entity.Atendente;
import com.uniamerica.pizzaria.repository.AtendenteRep;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AtendenteControllerTest {

    @MockBean
    AtendenteRep atendenteRep;

    @Autowired
    private  final AtendenteController atendenteController = new AtendenteController();




    @BeforeEach
    void injectFindByAll(){
        Atendente atendente = new Atendente(1L,"Sche");
        List<Atendente> atendentes = new ArrayList<>();

        atendentes.add(atendente);
        Mockito.when(atendenteRep.findAll()).thenReturn(atendentes);

    }



    @Test
    void findAll() {
        var atendentecontroler = atendenteController.findAll();
        List<Atendente> atendentes = atendentecontroler.getBody();
        int valor = 0;
        if (atendentes != null) {
            valor = atendentes.size();
        }
        System.out.println(valor);
        Assertions.assertEquals(1, valor, 0);
    }

    @Test
    void inserir() {
        Atendente atendente = new Atendente(1L, "Sche");
        var atendente1 = atendenteController.inserir(atendente);


       Assertions.assertNotNull(atendente1);
        Assertions.assertEquals("Atendente cadastrado(a) com sucesso!", atendente1.getBody());

    }

    @Test
    void updateAtendente() {

    }

    @Test
    void delete() {
        inserir();

        var delete = atendenteController.delete(1L);

        Assertions.assertNotNull(delete);

        Assertions.assertEquals("Atendente deletado com sucesso!", delete.getBody());

    }
}