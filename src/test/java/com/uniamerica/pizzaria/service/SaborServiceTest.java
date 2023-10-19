package com.uniamerica.pizzaria.service;

import com.uniamerica.pizzaria.entity.Sabor;
import com.uniamerica.pizzaria.repository.PizzaRep;
import com.uniamerica.pizzaria.repository.SaborRep;
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


@SpringBootTest
class SaborServiceTest {

    @MockBean
    SaborRep saborRep;
    @MockBean
    PizzaRep pizzaRep;


    @Autowired
    private final SaborService saborService = new SaborService();

    @BeforeEach
    void injectsabores(){

        Sabor sabor = new Sabor(1L,"Calabresa", "Calabresa");

        Sabor sabor2 = new Sabor(2L, "Strogonoff","frango");

        Sabor sabor3 = new Sabor(3L, "4 queijos","queijo");
        List<Sabor> sabores = new ArrayList<>();

        sabores.add(sabor);
        sabores.add(sabor2);
        sabores.add(sabor3);
        Mockito.when(saborRep.findAll()).thenReturn(sabores);


    }
    @Test
    void saveSabor() {

        Sabor sabor = new Sabor(1L,"Calabresa","Calabresa");
        var sabor1 =  saborService.saveSabor(sabor);
        Assertions.assertNotNull(sabor1);
        Assertions.assertEquals(HttpStatus.CREATED, sabor1.getStatusCode());
    }
}
