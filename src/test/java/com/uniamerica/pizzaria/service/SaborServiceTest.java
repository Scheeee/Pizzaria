package com.uniamerica.pizzaria.service;

import com.uniamerica.pizzaria.entity.Sabor;
import com.uniamerica.pizzaria.repository.PizzaRep;
import com.uniamerica.pizzaria.repository.SaborRep;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
    @Test
    void saveSabor() {
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);
        var sabor1 =  saborService.saveSabor(sabor);
        Assertions.assertNotNull(sabor1);
        Assertions.assertEquals(HttpStatus.CREATED, sabor1.getStatusCode());
    }
    @Test
    void saveSabores() {
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");

        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);
        List<String> ingredientes2 = new ArrayList<>();
        ingredientes2.add("frango");
        Sabor sabor2 = new Sabor(2L, "Strogonoff",ingredientes2);
        List<String> ingredientes3 = new ArrayList<>();
        ingredientes3.add("queijo");
        Sabor sabor3 = new Sabor(3L, "4 queijos",ingredientes3);
        var sabor1 =  saborService.saveSabor(sabor);
        Assertions.assertNotNull(sabor1);
        Assertions.assertEquals(HttpStatus.CREATED, sabor1.getStatusCode());
    }
}