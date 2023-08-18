package com.uniamerica.pizzaria.service;

import com.uniamerica.pizzaria.entity.Pedido;
import com.uniamerica.pizzaria.repository.PedidoRep;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    final PedidoRep pedidoRep;

    public PedidoService(PedidoRep pedidoRep) {
        this.pedidoRep = pedidoRep;
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<?> totais(String data) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(data, formatter);

        List<Pedido> hoje = pedidoRep.findByCadastrado(date);

        int pedidosDoDia = hoje.size();

        return  ResponseEntity.ok("Total de pedidos:" + pedidosDoDia);

    }
}
