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


    @Autowired
    private  final PedidoController pedidoController = new PedidoController();
    @Autowired
    private  final PedidoService pedidoService = new PedidoService(pedidoRep);

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

        Mockito.when(pedidoRep.findByAtendente(atendenteRep.getReferenceById(1L))).thenReturn(Optional.of(pedidos));
    }
    @BeforeEach
    void insertGetCliente(){
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

        Mockito.when(pedidoRep.findByCliente(clienteRep.getReferenceById(1L))).thenReturn(Optional.of(pedidos));
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


        Optional<Pedido> pedido = Optional.of(new Pedido(1L, atendente, cliente, pizzas, true, "Retirar cebolar", valor, false));

        Mockito.when(pedidoRep.findById(1L)).thenReturn(pedido);

    }
    @BeforeEach
    void injectGetByIdPedido(){
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


        Pedido pedido = new Pedido(1L, atendente, cliente, pizzas, true, "Retirar cebolar", valor, false);

        pedido.setStatus(Status.Ativo);
        Mockito.when(pedidoRep.getReferenceById(1L)).thenReturn(pedido);

    }
    @BeforeEach
    void injectGetByIdEncerrar(){
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


        Pedido pedido = new Pedido(2L, atendente, cliente, pizzas, true, "Retirar cebolar", valor, false);

        pedido.setStatus(Status.Ativo);
        Mockito.when(pedidoRep.getReferenceById(2L)).thenReturn(pedido);

    }
    @BeforeEach
    void injectGetAtivos(){
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


        Pedido pedido = new Pedido(1L, atendente, cliente, pizzas, true, "Retirar cebolar", valor, false);

        List<Pedido> pedidos = new ArrayList<>();
        pedido.setStatus(Status.Ativo);
        pedidos.add(pedido);
        Mockito.when(pedidoRep.findByStatus(Status.Ativo)).thenReturn(pedidos);

    }
    @BeforeEach
    void injectGetAll(){
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


        Pedido pedido = new Pedido(1L, atendente, cliente, pizzas, true, "Retirar cebolar", valor, false);

        List<Pedido> pedidos = new ArrayList<>();
        pedido.setStatus(Status.Ativo);
        pedidos.add(pedido);
        Mockito.when(pedidoRep.findAll()).thenReturn(pedidos);

    }



    @Test
    void findByData() {


        var pedidocontroller = pedidoController.findByData("18-09-2023");



        Assertions.assertNotNull(pedidocontroller);
        Assertions.assertEquals("""
                Total de pedidos: 1
                Total de pedidos encerrados: 0
                Total de pedidos cancelados: 0
                Pedidos entregues: 0
                Pedidos retirados: 0
                Faturamento total: 0
                Pedidos pagos em dinheiro: 0
                Pedidos pagos no cartão: 0""", pedidocontroller.getBody());
    }

    @Test
    void getatendente() {
        var pedidocontroller = pedidoController.getatendente(1L);

        Pedido pedido = pedidocontroller.getBody().get().get(0);
        long id = pedido.getAtendente().getId();
        System.out.println(id);
        Assertions.assertEquals(1L, id, 0);
    }

    @Test
    void getCliente() {
        var pedidocontroller = pedidoController.getCliente(1L);

        Pedido pedido = pedidocontroller.getBody().get().get(0);
        long id = pedido.getCliente().getId();
        System.out.println(id);
        Assertions.assertEquals(1L, id, 0);
    }

    @Test
    void findById() {
        var pedidocontroller = pedidoController.findById(1L);
        Long id = pedidocontroller.getBody().get().getId();
        System.out.println(id);
        Assertions.assertEquals(1L, id, 0);

    }

    @Test
    void comanda() {
        var comanda = pedidoController.comanda(1L);

        Assertions.assertNotNull(comanda);
        Assertions.assertEquals("comanda gerada com sucesso!", comanda.getBody());
    }
    @Test
    void getativos() {

        var pedidocontroller = pedidoController.getativos();

        long id = pedidocontroller.getBody().get(0).getId();

        System.out.println(id);
        Assertions.assertEquals(1L, id, 0);
    }

    @Test
    void getAll() {
        var pedidocontroller = pedidoController.getAll();
        List<Pedido> pedidos = (List<Pedido>) pedidocontroller.getBody();

        int valor = pedidos.size();

        System.out.println(valor);
        Assertions.assertEquals(1, valor, 0);
    }

    @Test
    void inserir() {
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


        Pedido pedido = new Pedido(1L, atendente, cliente, pizzas, true, "Retirar cebolar", valor, false);

        var pedido1 = pedidoController.inserir(pedido);


        Assertions.assertNotNull(pedido1);
        Assertions.assertEquals("pedido cadastrado com sucesso!", pedido1.getBody());
    }


    @Test
    void updatePedido() {
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


        Pedido pedido = new Pedido(1L, atendente, cliente, pizzas, true, "Retirar cebolar", valor, false);


        var pedido1 = pedidoController.updatePedido(1L,pedido);


        Assertions.assertNotNull(pedido1);
        Assertions.assertEquals("pedido atualizado com sucesso!", pedido1.getBody());


    }

    @Test
    void cancelarPedido() {


        var pedido1 = pedidoController.cancelarPedido(1L);


        Assertions.assertNotNull(pedido1);
        Assertions.assertEquals("pedido cancelado com sucesso!", pedido1.getBody());
    }

    @Test
    void encerrarPedido() {
        var pedido1 = pedidoController.encerrarPedido(2L);


        Assertions.assertNotNull(pedido1);
        Assertions.assertEquals("pedido encerrado com sucesso!", pedido1.getBody());
    }

    @Test
    void delete() {
        inserir();

        var delete = pedidoController.delete(1L);

        Assertions.assertNotNull(delete);

        Assertions.assertEquals("Pedido deletado com sucesso!", delete.getBody());
    }

}