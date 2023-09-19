package com.uniamerica.pizzaria.service;
import com.uniamerica.pizzaria.entity.*;
import com.uniamerica.pizzaria.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class PedidoServiceTest {

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
    private final PedidoService pedidoService = new PedidoService(pedidoRep);

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
    void injectEncerrar(){
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
        Mockito.when(pedidoRep.getById(1L)).thenReturn(pedido);

    }

    @Test
    void totais() {

        var pedidoservice = pedidoService.totais("18-09-2023");
        Assertions.assertNotNull(pedidoservice);
        Assertions.assertEquals("""
                        Total de pedidos: 1
                        Total de pedidos encerrados: 0
                        Total de pedidos cancelados: 0
                        Pedidos entregues: 0
                        Pedidos retirados: 0
                        Faturamento total: 0
                        Pedidos pagos em dinheiro: 0
                        Pedidos pagos no cartão: 0""",
                pedidoservice);
    }


    @Test
    void encerrar() {

        var pedido1 = pedidoService.encerrar(1L);


        Assertions.assertNotNull(pedido1);
        Assertions.assertEquals(HttpStatus.OK, pedido1.getStatusCode());

    }

    @Test
    void salvarPedidoEncerrado() {
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


        var pedido1 = pedidoService.salvarPedidoEncerrado(pedido);



        Assertions.assertNotNull(pedido1);
        Assertions.assertEquals(HttpStatus.OK, pedido1.getStatusCode());
    }

    @Test
    void gerarComanda() {

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


        var pedido1 = pedidoService.gerarComanda(pedido);



        Assertions.assertNotNull(pedido1);
        Assertions.assertEquals(HttpStatus.OK, pedido1.getStatusCode());

    }
}