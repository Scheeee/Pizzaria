package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.entity.*;
import com.uniamerica.pizzaria.repository.ClienteRep;
import com.uniamerica.pizzaria.repository.PizzaRep;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PizzaControllerTest {
    @MockBean
    PizzaRep pizzaRep;

    @Autowired
    private final PizzaController pizzaController = new PizzaController();


    @BeforeEach
    void injectFindByAll(){
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);
        List<Sabor> sabores = new ArrayList<>();
        sabores.add(sabor);

        BigDecimal valor = new BigDecimal(25);
        Pizza pizza = new Pizza(1L, Tamanho.P,sabores,valor);

        List<Pizza> pizzas = new ArrayList<>();

        pizzas.add(pizza);
        Mockito.when(pizzaRep.findAll()).thenReturn(pizzas);

    }

    @Test
    void getAll() {
        var pizzacontroller = pizzaController.getAll();
        List<Pizza> pizzas = (List<Pizza>) pizzacontroller.getBody();
        int valor = pizzas.size();
        System.out.println(valor);
        Assert.assertEquals(1, valor, 0);
    }

    @Test
    void inserir() {
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);
        List<Sabor> sabores = new ArrayList<>();
        sabores.add(sabor);

        BigDecimal valor = new BigDecimal(25);
        Pizza pizza = new Pizza(1L, Tamanho.P,sabores,valor);
        var pizza1 = pizzaController.inserir(pizza);


        Assert.assertNotNull(pizza1);
        Assert.assertEquals("Pizza cadastrada com sucesso", pizza1.getBody());
    }

    @Test
    void updatePizza() {
    }

    @Test
    void delete() {
        inserir();

        var delete = pizzaController.delete(1L);

        Assert.assertNotNull(delete);
        Assert.assertEquals("Pizza deletada com sucesso!", delete.getBody());
    }
}