package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.dto.PizzaDTO;
import com.uniamerica.pizzaria.entity.*;

import com.uniamerica.pizzaria.repository.PizzaRep;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



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
    @BeforeEach
    void injectUpdate(){
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);
        List<Sabor> sabores = new ArrayList<>();
        sabores.add(sabor);

        BigDecimal valor = new BigDecimal(25);
        Pizza pizza = new Pizza(1L, Tamanho.P,sabores,valor);


        Mockito.when(pizzaRep.getReferenceById(1L)).thenReturn(pizza);

    }

    @Test
    void getAll() {
        var pizzacontroller = pizzaController.getAll();
        List<Pizza> pizzas = (List<Pizza>) pizzacontroller.getBody();
        assert pizzas != null;
        int valor = pizzas.size();
        System.out.println(valor);
        Assertions.assertEquals(1, valor, 0);
    }

    @Test
    void inserir() {
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);
        List<Sabor> sabores = new ArrayList<>();
        sabores.add(sabor);

        BigDecimal valor = new BigDecimal(25);
        PizzaDTO pizza = new PizzaDTO(1L, Tamanho.P,sabores,valor);
        var pizza1 = pizzaController.inserir(pizza);


        Assertions.assertNotNull(pizza1);
        Assertions.assertEquals("Pizza cadastrada com sucesso", pizza1.getBody());
    }

    @Test
    void inserirErro(){
        var pizza1 = pizzaController.inserir(new PizzaDTO());


        Assertions.assertNotNull(pizza1);
        Assertions.assertEquals("Error: null", pizza1.getBody());
    }
    @Test
    void updatePizza() {
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa com alho", ingredientes);
        List<Sabor> sabores = new ArrayList<>();
        sabores.add(sabor);

        BigDecimal valor = new BigDecimal(25);
        PizzaDTO pizza = new PizzaDTO(1L, Tamanho.P,sabores,valor);
        var pizza1 = pizzaController.updatePizza(1L,pizza);


        Assertions.assertNotNull(pizza1);
        Assertions.assertEquals("pizza atualizada com sucesso!", pizza1.getBody());

    }

    @Test
    void delete() {
        inserir();

        var delete = pizzaController.delete(1L);

        Assertions.assertNotNull(delete);
        Assertions.assertEquals("Pizza deletada com sucesso!", delete.getBody());
    }
}