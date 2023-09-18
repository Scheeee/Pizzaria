package com.uniamerica.pizzaria.entity;

import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    Atendente atendente = new Atendente(1, "Sche");
    Cliente cliente = new Cliente(1,"Sche", "45-98034-3600");
    Endereco endereco = new Endereco(1,"Avenida tancredo neves","1234", "casa 123", cliente);

    BigDecimal valor = new BigDecimal("25");

    Sabor sabor = new Sabor(1, "Calabresa", Collections.singletonList("Calabresa"));
    Pizza pizza = new Pizza(1, Tamanho.P, Collections.singletonList(sabor), valor);

    Pedido pedido = new Pedido(1, atendente, cliente, Collections.singletonList(pizza), false, "retirar cebola",valor, true);
    @Test
    void getId() {
        long id = pedido.getId();

        Assert.assertEquals(1L, id, 0);

    }

    @Test
    void getCadastrado() {

    }

    @Test
    void getFinalizado() {
    }

    @Test
    void getAtendente() {
        long id = pedido.getAtendente().getId();
        Assert.assertEquals(1L, id, 0);


    }

    @Test
    void getCliente() {
        long id = pedido.getCliente().getId();

        Assert.assertEquals(1L, id, 0);
    }

    @Test
    void getPizzas() {
        Pizza pizzas= pedido.getPizzas().get(0);

        long id = pizzas.getId();

        Assert.assertEquals(1L, id, 0);



    }

    @Test
    void isEntrega() {

        Boolean entrega = pedido.isEntrega();
        Assert.assertEquals(false, entrega);
    }

    @Test
    void getDetalhes() {
    }

    @Test
    void getAdicionais() {
    }

    @Test
    void getValorTotal() {
        BigDecimal total = pedido.getValorTotal();

        Assert.assertEquals(valor,total);
    }

    @Test
    void isDinheiro() {

        Boolean dinheiro = pedido.isDinheiro();

        Assert.assertEquals(true, dinheiro);
    }

    @Test
    void getStatus() {

        //Status status = pedido.getStatus();

       // Assert.assertEquals(Status.Ativo, status);
    }

    @Test
    void setCadastrado() {
    }

    @Test
    void setFinalizado() {
    }

    @Test
    void setAtendente() {
        Atendente atendente1 = new Atendente(2, "Sche");

        pedido.setAtendente(atendente1);
        long id = pedido.getAtendente().getId();
        Assert.assertEquals(2L, id, 0);

    }

    @Test
    void setCliente() {
        Cliente cliente1 = new Cliente(2,"Sche", "45-98034-3600");

        pedido.setCliente(cliente1);
        long id = pedido.getCliente().getId();

        Assert.assertEquals(2L, id, 0);
    }

    @Test
    void setPizzas() {
        Pizza pizza1 = new Pizza(2, Tamanho.P, Collections.singletonList(sabor), valor);

        List<Pizza> pizzaList = new ArrayList<>();

        pizzaList.add(pizza1);


        pedido.setPizzas(pizzaList);
        Pizza pizzas= pedido.getPizzas().get(0);

        long id = pizzas.getId();

        Assert.assertEquals(2L, id, 0);
    }

    @Test
    void setEntrega() {
        pedido.setEntrega(true);
        Boolean entrega = pedido.isEntrega();
        Assert.assertEquals(true, entrega);
    }

    @Test
    void setDetalhes() {
    }

    @Test
    void setAdicionais() {
    }

    @Test
    void setValorTotal() {
        BigDecimal valor1 = BigDecimal.valueOf(24);

        pedido.setValorTotal(valor1);
        BigDecimal total = pedido.getValorTotal();

        Assert.assertEquals(valor1,total);
    }

    @Test
    void setDinheiro() {
        pedido.setDinheiro(false);
        Boolean dinheiro = pedido.isDinheiro();

        Assert.assertEquals(false, dinheiro);

    }

    @Test
    void setStatus() {
    }
}