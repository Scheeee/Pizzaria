package com.uniamerica.pizzaria.controller;


import com.uniamerica.pizzaria.entity.*;
import com.uniamerica.pizzaria.repository.*;
import com.uniamerica.pizzaria.service.PedidoService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@SpringBootTest
class PedidoControllerTest {

    @MockBean
    PedidoRep pedidoRep;
    @MockBean
    AtendenteRep atendenteRep;
    @MockBean
    EnderecoRep enderecoRep;
    @MockBean
    ClienteRep clienteRep;
    @MockBean
    PizzaRep pizzaRep;
    @MockBean
    SaborRep saborRep;
    @MockBean
    PedidoService pedidoService;

    @Autowired
    private  final PedidoController pedidoController = new PedidoController();

    @BeforeEach
    void insertFindByData(){
        Atendente atendente = new Atendente(1L,"Sche");
        Endereco endereco = new Endereco(1,"Avenida tancredo neves","1234", "casa 123");
        Cliente cliente = new Cliente(1,"Sche", "45-98034-3600", endereco);
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);
        List<Sabor> sabores = new ArrayList<>();
        sabores.add(sabor);
        BigDecimal valor = new BigDecimal(25);
        Pizza pizza = new Pizza(1L, Tamanho.P,sabores,valor);
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(pizza);

        String dataString = "18-09-2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate cadastro = LocalDate.parse(dataString, formatter);

        Pedido pedido = new Pedido(1L,atendente,cliente,pizzas,true,"Retirar cebolar", valor, false, cadastro);


        List<Pedido> pedidos = new ArrayList<>();


        pedidos.add(pedido);

        Mockito.when(pedidoRep.findByCadastrado(cadastro)).thenReturn(pedidos);
    }

    @BeforeEach
    void insertGetAtendente(){
        Atendente atendente = new Atendente(1L,"Sche");
        Endereco endereco = new Endereco(1,"Avenida tancredo neves","1234", "casa 123");
        Cliente cliente = new Cliente(1,"Sche", "45-98034-3600", endereco);
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);
        List<Sabor> sabores = new ArrayList<>();
        sabores.add(sabor);
        BigDecimal valor = new BigDecimal(25);
        Pizza pizza = new Pizza(1L, Tamanho.P,sabores,valor);
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(pizza);

        String dataString = "18-09-2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate cadastro = LocalDate.parse(dataString, formatter);

        Pedido pedido = new Pedido(1L,atendente,cliente,pizzas,true,"Retirar cebolar", valor, false, cadastro);


        List<Pedido> pedidos = new ArrayList<>();


        pedidos.add(pedido);

        Mockito.when(pedidoRep.findByAtendente(Mockito.any(Atendente.class))).thenReturn(pedidos);
    }
    @BeforeEach
    void injectFindByIdPessoa(){
        Atendente atendente = new Atendente(1L,"Sche");
        Endereco endereco = new Endereco(1,"Avenida tancredo neves","1234", "casa 123");
        Cliente cliente = new Cliente(1,"Sche", "45-98034-3600", endereco);
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);
        List<Sabor> sabores = new ArrayList<>();
        sabores.add(sabor);
        BigDecimal valor = new BigDecimal(25);
        Pizza pizza = new Pizza(1L, Tamanho.P,sabores,valor);
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(pizza);

        String dataString = "18-09-2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate cadastro = LocalDate.parse(dataString, formatter);

        Optional<Pedido> pedido = Optional.of(new Pedido(1L, atendente, cliente, pizzas, true, "Retirar cebolar", valor, false, cadastro));



        Mockito.when(pedidoRep.findById(1L)).thenReturn(pedido);

    }



    @Test
    void findByData() {

        var pedidocontroller = pedidoController.findByData("18-09-2023");

        List<Pedido> pedidos = (List<Pedido>) pedidocontroller.getBody();
        assert pedidos != null;
        int valor = pedidos.size();
        System.out.println(valor);
        Assertions.assertEquals(1, valor, 0);
    }

    @Test
    void getatendente() {
      //  var pedidocontroller = pedidoController.getatendente(1L);

        //List<Pedido> pedidos = pedidocontroller.getBody().get();

        //ong id = pedidos.get(0).getId();

        //Assertions.assertEquals(1, id, 0);
    }

    @Test
    void getCliente() {
    }

    @Test
    void findById() {
     /*   var pedidocontroller = pedidoController.findById(1L);
        Long id = pedidocontroller.getBody().get;
        System.out.println(id);
        Assert.assertEquals(1L, id, 0);*/
    }

    @Test
    void getativos() {
    }

    @Test
    void getAll() {
    }

    @Test
    void inserir() {
    }

    @Test
    void updatePedido() {
    }

    @Test
    void cancelarPedido() {
    }

    @Test
    void encerrarPedido() {
    }

    @Test
    void delete() {
    }
}