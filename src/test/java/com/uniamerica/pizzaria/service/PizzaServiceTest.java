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

import static org.junit.Assert.assertThrows;


@SpringBootTest
class PizzaServiceTest {
    @MockBean
    PizzaRep pizzaRep;

    @Autowired
    private final PizzaService pizzaService = new PizzaService();
    @BeforeEach
    void injectSave(){

        Sabor sabor = new Sabor(1L,"Calabresa", "Calabresa");
        List<Sabor> sabores = new ArrayList<>();
        sabores.add(sabor);

        BigDecimal valor = new BigDecimal(25);
        Pizza pizza = new Pizza(1L, Tamanho.P,sabores,valor);


        Mockito.when(pizzaRep.getReferenceById(1L)).thenReturn(pizza);

    }

    @Test
    void saveP() {

        Sabor sabor = new Sabor(1L,"Calabresa", "Calabresa");
        List<Sabor> sabores = new ArrayList<>();
        sabores.add(sabor);

        BigDecimal valor = new BigDecimal(25);
        Pizza pizza = new Pizza(1L, Tamanho.P,sabores,valor);

        var pizzaservice = pizzaService.save(pizza);

        Assertions.assertNotNull(pizzaservice);
        Assertions.assertEquals(HttpStatus.CREATED, pizzaservice.getStatusCode());
    }
    @Test
    void saveM() {

        Sabor sabor = new Sabor(1L,"Calabresa", "Calabresa");
        Sabor sabor2 = new Sabor(2L, "Portuguesa", "Calabresa");
        List<Sabor> sabores = new ArrayList<>();
        sabores.add(sabor);
        sabores.add(sabor2);

        BigDecimal valor = new BigDecimal(25);
        Pizza pizza = new Pizza(1L, Tamanho.M,sabores,valor);

        var pizzaservice = pizzaService.save(pizza);

        Assertions.assertNotNull(pizzaservice);
        Assertions.assertEquals(HttpStatus.CREATED, pizzaservice.getStatusCode());
    }
    @Test
    void saveG() {

        Sabor sabor = new Sabor(1L,"Calabresa", "Calabresa");

        Sabor sabor2 = new Sabor(2L, "Portuguesa","Alguma coisa");

        Sabor sabor3 = new Sabor(2L, "Strogonoff", "frango");
        List<Sabor> sabores = new ArrayList<>();
        sabores.add(sabor);
        sabores.add(sabor2);
        sabores.add(sabor3);

        BigDecimal valor = new BigDecimal(25);
        Pizza pizza = new Pizza(1L, Tamanho.G,sabores,valor);

        var pizzaservice = pizzaService.save(pizza);

        Assertions.assertNotNull(pizzaservice);
        Assertions.assertEquals(HttpStatus.CREATED, pizzaservice.getStatusCode());
    }
    @Test
    void saveGG() {

        Sabor sabor = new Sabor(1L,"Calabresa", "Calabresa");

        Sabor sabor2 = new Sabor(2L, "Portuguesa", "Alguma coisa");

        Sabor sabor3 = new Sabor(2L, "Strogonoff", "Frango");

        Sabor sabor4 = new Sabor(2L, "4 queijos","4 queijos");

        List<Sabor> sabores = new ArrayList<>();
        sabores.add(sabor);
        sabores.add(sabor2);
        sabores.add(sabor3);
        sabores.add(sabor4);


        BigDecimal valor = new BigDecimal(25);
        Pizza pizza = new Pizza(1L, Tamanho.GG,sabores,valor);

        var pizzaservice = pizzaService.save(pizza);

        Assertions.assertNotNull(pizzaservice);
        Assertions.assertEquals(HttpStatus.CREATED, pizzaservice.getStatusCode());
    }
    @Test
    void erro(){

        List<Sabor> sabores = new ArrayList<>();

        BigDecimal valor = new BigDecimal(25);
        Pizza pizza = new Pizza(1L, Tamanho.P, sabores, valor);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> pizzaService.save(pizza));


        Assertions.assertEquals("insira um sabor", exception.getMessage());


    }
}
