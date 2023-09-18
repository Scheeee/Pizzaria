package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.repository.PedidoRep;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PedidoControllerTest {

    @MockBean
    PedidoRep pedidoRep;

    @Autowired
    private  final PedidoController pedidoController = new PedidoController();



    @Test
    void findByData() {
    }

    @Test
    void getatendente() {
    }

    @Test
    void getCliente() {
    }

    @Test
    void findById() {
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