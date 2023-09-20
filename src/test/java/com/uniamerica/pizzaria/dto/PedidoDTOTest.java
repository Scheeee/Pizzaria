package com.uniamerica.pizzaria.dto;

import com.uniamerica.pizzaria.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class PedidoDTOTest {
    Atendente atendente = new Atendente(1, "Sche");
    Endereco endereco = new Endereco(1,"Avenida tancredo neves","1234", "casa 123");
    Cliente cliente = new Cliente(1,"Sche", "45-98034-3600", endereco);
    BigDecimal valor = new BigDecimal("25");

    Sabor sabor = new Sabor(1, "Calabresa", Collections.singletonList("Calabresa"));
    Pizza pizza = new Pizza(1, Tamanho.P, Collections.singletonList(sabor), valor);

    PedidoDTO pedido = new PedidoDTO(1, atendente, cliente, Collections.singletonList(pizza), false, "retirar cebola",valor, true);


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
    void getFinalizadoandSetFinalizando() {

        String dataString = "18-09-2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate data = LocalDate.parse(dataString, formatter);
        pedido.setFinalizado(data);


        Assertions.assertEquals(data,
                pedido.getFinalizado());


    }

    @Test
    void getAtendenteandSetAtendente() {

        Atendente atendente1 = new Atendente(2, "Gabi");

        pedido.setAtendente(atendente1);

        Assertions.assertEquals(atendente1, pedido.getAtendente());


    }

    @Test
    void getClienteandSetCliente() {
        Cliente cliente1 = new Cliente(2,"Carlos", "45-97454-3600", endereco);
        pedido.setCliente(cliente1);

        Assertions.assertEquals(cliente1, pedido.getCliente());

    }

    @Test
    void getPizzasandSetPizzas() {
        Pizza pizza1 = new Pizza(1, Tamanho.G, Collections.singletonList(sabor), valor);
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(pizza1);
        pedido.setPizzas(pizzas);

        Assertions.assertEquals(pizzas, pedido.getPizzas());

    }

    @Test
    void isEntregaSetEntrega() {

        pedido.setEntrega(true);

        Assertions.assertTrue(pedido.isEntrega());
    }

    @Test
    void getDetalhesandSetDetalhes() {

        pedido.setDetalhes("adicionar molho extra");

        Assertions.assertEquals("adicionar molho extra", pedido.getDetalhes());
    }

    @Test
    void getValorTotalandSetValorTotal() {
        BigDecimal valor1 = new BigDecimal(40);
        pedido.setValorTotal(valor1);

        Assertions.assertEquals(valor1, pedido.getValorTotal());
    }

    @Test
    void isDinheiroandSetDinheiro() {
        pedido.setDinheiro(false);

        Assertions.assertFalse(pedido.isDinheiro());
    }

    @Test
    void getStatusandSetStatus() {
        pedido.setStatus(Status.CANCELADO);

        Assertions.assertEquals(Status.CANCELADO, pedido.getStatus());
    }

    @Test
    void setIdandGetId() {
        pedido.setId(3);

        Assertions.assertEquals(3, pedido.getId());
    }
    @Test
    void testEquals() {
        PedidoDTO pedido2 = new PedidoDTO(1, atendente, cliente, Collections.singletonList(pizza), false, "retirar cebola",valor, true);
        Assertions.assertEquals(pedido, pedido2);
    }

    @Test
    void canEqual() {
        PedidoDTO pedido2 = new PedidoDTO(1, atendente, cliente, Collections.singletonList(pizza), false, "retirar cebola",valor, true);
        Assertions.assertTrue(pedido.canEqual(pedido2));
    }
    @Test
    void testHashCode(){
        PedidoDTO pedido2 = new PedidoDTO(1, atendente, cliente, Collections.singletonList(pizza), false, "retirar cebola",valor, true);
        Assertions.assertEquals(pedido.hashCode(), pedido2.hashCode());
    }
    @Test
    void testToString(){
        PedidoDTO pedido2 = new PedidoDTO(1, atendente, cliente, Collections.singletonList(pizza), false, "retirar cebola",valor, true);

        Assertions.assertEquals(pedido2.toString(), pedido.toString());
    }
    @Test
    void TestNull(){
        PedidoDTO pedidoDTO = new PedidoDTO();

        Assertions.assertEquals(0, pedidoDTO.getId());

    }




}