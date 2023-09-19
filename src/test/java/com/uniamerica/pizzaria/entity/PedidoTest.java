package com.uniamerica.pizzaria.entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class PedidoTest {


    Atendente atendente = new Atendente(1, "Sche");
    Endereco endereco = new Endereco(1,"Avenida tancredo neves","1234", "casa 123");
    Cliente cliente = new Cliente(1,"Sche", "45-98034-3600", endereco);
    BigDecimal valor = new BigDecimal("25");

    Sabor sabor = new Sabor(1, "Calabresa", Collections.singletonList("Calabresa"));
    Pizza pizza = new Pizza(1, Tamanho.P, Collections.singletonList(sabor), valor);

    Pedido pedido = new Pedido(1, atendente, cliente, Collections.singletonList(pizza), false, "retirar cebola",valor, true);
    @Test
    void getId() {
        long id = pedido.getId();

        Assertions.assertEquals(1L, id, 0);

    }

    @Test
    void getCadastradoandSetCadastrado() {
        String dataString = "18-09-2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate cadastro1 = LocalDate.parse(dataString, formatter);
        pedido.setCadastrado(cadastro1);


        Assertions.assertEquals(cadastro1,
                pedido.getCadastrado());

    }

    @Test
    void getFinalizadoandSetFinalizado() {
        String dataString = "18-09-2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate data = LocalDate.parse(dataString, formatter);
        pedido.setFinalizado(data);


        Assertions.assertEquals(data,
                pedido.getFinalizado());
    }

    @Test
    void getAtendente() {
        long id = pedido.getAtendente().getId();
        Assertions.assertEquals(1L, id, 0);


    }

    @Test
    void getCliente() {
        long id = pedido.getCliente().getId();

        Assertions.assertEquals(1L, id, 0);
    }

    @Test
    void getPizzas() {
        Pizza pizzas= pedido.getPizzas().get(0);

        long id = pizzas.getId();

        Assertions.assertEquals(1L, id, 0);



    }

    @Test
    void isEntrega() {

        Boolean entrega = pedido.isEntrega();
        Assertions.assertEquals(false, entrega);
    }

    @Test
    void getDetalhesandSetDetalhes() {

        pedido.setDetalhes("adicionar molho extra");

        Assertions.assertEquals("adicionar molho extra", pedido.getDetalhes());
    }

    @Test
    void getValorTotal() {
        BigDecimal total = pedido.getValorTotal();

        Assertions.assertEquals(valor, total);
    }

    @Test
    void isDinheiro() {

        Boolean dinheiro = pedido.isDinheiro();

        Assertions.assertEquals(true, dinheiro);
    }

    @Test
    void getStatusandSetStatus() {
        pedido.setStatus(Status.Cancelado);

        Assertions.assertEquals(Status.Cancelado, pedido.getStatus());

    }


    @Test
    void setAtendente() {
        Atendente atendente1 = new Atendente(2, "Sche");

        pedido.setAtendente(atendente1);
        long id = pedido.getAtendente().getId();
        Assertions.assertEquals(2L, id, 0);

    }

    @Test
    void setCliente() {
        Cliente cliente1 = new Cliente(2,"Sche", "45-98034-3600");

        pedido.setCliente(cliente1);
        long id = pedido.getCliente().getId();

        Assertions.assertEquals(2L, id, 0);
    }

    @Test
    void setPizzas() {
        Pizza pizza1 = new Pizza(2, Tamanho.P, Collections.singletonList(sabor), valor);

        List<Pizza> pizzaList = new ArrayList<>();

        pizzaList.add(pizza1);


        pedido.setPizzas(pizzaList);
        Pizza pizzas= pedido.getPizzas().get(0);

        long id = pizzas.getId();

        Assertions.assertEquals(2L, id, 0);
    }

    @Test
    void setEntrega() {
        pedido.setEntrega(true);
        Boolean entrega = pedido.isEntrega();
        Assertions.assertEquals(true, entrega);
    }


    @Test
    void setValorTotal() {
        BigDecimal valor1 = BigDecimal.valueOf(24);

        pedido.setValorTotal(valor1);
        BigDecimal total = pedido.getValorTotal();

        Assertions.assertEquals(valor1, total);
    }

    @Test
    void setDinheiro() {
        pedido.setDinheiro(false);
        Boolean dinheiro = pedido.isDinheiro();

        Assertions.assertEquals(false, dinheiro);

    }


}