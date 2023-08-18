package com.uniamerica.pizzaria.service;

import com.uniamerica.pizzaria.entity.Pedido;
import com.uniamerica.pizzaria.entity.Status;
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
        List<Pedido> encerrado = pedidoRep.findByStatusAndCadastrado(Status.Encerrado, date);
        int pedidosEncerrados = encerrado.size();
        List<Pedido> cancelado = pedidoRep.findByStatusAndCadastrado(Status.Cancelado, date);
        int pedidosCancelados = cancelado.size();
        List<Pedido> entregue = pedidoRep.findByEntregaAndStatusAndCadastrado(true,Status.Encerrado, date);
        int pedidosEntregues = entregue.size();
        List<Pedido> retirado = pedidoRep.findByEntregaAndStatusAndCadastrado(false,Status.Encerrado, date);
        int pedidosRetirados = retirado.size();
        List<Pedido> dinheiro = pedidoRep.findByStatusAndCadastradoAndDinheiro(Status.Encerrado, date, true);
        int pedidosDinheiro = dinheiro.size();
        List<Pedido> cartao = pedidoRep.findByStatusAndCadastradoAndDinheiro(Status.Encerrado, date, false);
        int pedidosCartao = cartao.size();





        return  ResponseEntity.ok("Total de pedidos:" + pedidosDoDia + "\n Total de pedidos encerrados: " + pedidosEncerrados +"Total de pedidos cancelados: "+ pedidosCancelados + "\n Pedidos entregues:" + pedidosEntregues + "\n Pedidos  retirados: "+ pedidosRetirados + "\n Pedidos pagos em dinheiro :" + pedidosDinheiro + "\n Pedidos pagos no cartão:" + pedidosCartao);

    }
}
