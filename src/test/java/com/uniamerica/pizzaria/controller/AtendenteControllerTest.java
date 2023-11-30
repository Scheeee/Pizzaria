package com.uniamerica.pizzaria.controller;
import com.uniamerica.pizzaria.dto.AtendenteDTO;
import com.uniamerica.pizzaria.entity.Atendente;
import com.uniamerica.pizzaria.repository.AtendenteRep;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class AtendenteControllerTest {
/*
    @MockBean
    AtendenteRep atendenteRep;

    @Autowired
    private  final AtendenteController atendenteController = new AtendenteController();

    @BeforeEach
    void injectFindByAll(){
        Atendente atendente = new Atendente(1L,"Sche");
        List<Atendente> atendentes = new ArrayList<>();

        atendentes.add(atendente);
        when(atendenteRep.findAll()).thenReturn(atendentes);

    }
    @BeforeEach
    void injectUpdate(){
        Atendente atendente = new Atendente(1L,"Sche");

        when(atendenteRep.getReferenceById(1L)).thenReturn(atendente);

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
        AtendenteDTO atendente = new AtendenteDTO(1, "Sche", "senha", "role");
        var atendente1 = atendenteController.inserir(atendente);


       Assertions.assertNotNull(atendente1);
        Assertions.assertEquals("Atendente cadastrado(a) com sucesso!", atendente1.getBody());

    }
    @Test
    void inserirErrado() {

        var atendente1 = atendenteController.inserir(new AtendenteDTO());


        Assertions.assertNotNull(atendente1);
        Assertions.assertEquals("Error: null", atendente1.getBody());

    }

    @Test
    void updateAtendente() {
        AtendenteDTO atendente = new AtendenteDTO(1L, "Gabriele");
        var atendente1 = atendenteController.updateAtendente(1L,atendente);


        Assertions.assertNotNull(atendente1);
        Assertions.assertEquals("Atendente atualizado com sucesso!", atendente1.getBody());

    }

    @Test
    void delete() {
        inserir();

        var delete = atendenteController.delete(1L);

        Assertions.assertNotNull(delete);

        Assertions.assertEquals("Atendente deletado com sucesso!", delete.getBody());

    }*/
}
