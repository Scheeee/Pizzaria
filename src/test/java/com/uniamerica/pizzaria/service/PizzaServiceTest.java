package com.uniamerica.pizzaria.service;

import com.uniamerica.pizzaria.entity.Pizza;
import com.uniamerica.pizzaria.entity.Sabor;
import com.uniamerica.pizzaria.entity.Tamanho;
import com.uniamerica.pizzaria.repository.PizzaRep;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class PizzaServiceTest {
    @MockBean
    PizzaRep pizzaRep;

    @Autowired
    private final PizzaService pizzaService = new PizzaService();
    @BeforeEach
    void injectSave(){
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);
        List<Sabor> sabores = new ArrayList<>();
        sabores.add(sabor);

        BigDecimal valor = new BigDecimal(25);
        Pizza pizza = new Pizza(1L, Tamanho.P,sabores,valor);


        Mockito.when(pizzaRep.getById(1L)).thenReturn(pizza);

    }

    @Test
    void save() {
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);
        List<Sabor> sabores = new ArrayList<>();
        sabores.add(sabor);

        BigDecimal valor = new BigDecimal(25);
        Pizza pizza = new Pizza(1L, Tamanho.P,sabores,valor);

        var pizzaservice = pizzaService.save(pizza);

        Assertions.assertNotNull(pizzaservice);
        Assertions.assertEquals(HttpStatus.CREATED, pizzaservice.getStatusCode());
    }
}