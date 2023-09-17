package com.uniamerica.pizzaria;

import com.uniamerica.pizzaria.controller.AtendenteController;
import com.uniamerica.pizzaria.entity.Atendente;
import com.uniamerica.pizzaria.repository.AtendenteRep;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AtendenteTest {
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
    void testFindAll(){
        var atendentecontroler = atendenteController.findAll();
        List<Atendente> atendentes = (List<Atendente>) atendentecontroler.getBody();
        int valor = atendentes.size();
        System.out.println(valor);
        Assert.assertEquals(1, valor, 0);

    }




}
