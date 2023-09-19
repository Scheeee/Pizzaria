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
    void saveP() {
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
    @Test
    void saveM() {
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);
        List<String> ingredientes2 = new ArrayList<>();
        ingredientes.add("Alguma coisa");
        Sabor sabor2 = new Sabor(2L, "Portuguesa", ingredientes2);
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
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);
        List<String> ingredientes2 = new ArrayList<>();
        ingredientes.add("Alguma coisa");
        Sabor sabor2 = new Sabor(2L, "Portuguesa", ingredientes2);
        List<String> ingredientes3 = new ArrayList<>();
        ingredientes.add("Frango");
        Sabor sabor3 = new Sabor(2L, "Strogonoff", ingredientes3);
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
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);
        List<String> ingredientes2 = new ArrayList<>();
        ingredientes.add("Alguma coisa");
        Sabor sabor2 = new Sabor(2L, "Portuguesa", ingredientes2);
        List<String> ingredientes3 = new ArrayList<>();
        ingredientes.add("Frango");
        Sabor sabor3 = new Sabor(2L, "Strogonoff", ingredientes3);
        List<String> ingredientes4 = new ArrayList<>();
        ingredientes.add("4 queijos");
        Sabor sabor4 = new Sabor(2L, "4 queijos", ingredientes4);

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