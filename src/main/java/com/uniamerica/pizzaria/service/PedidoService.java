package com.uniamerica.pizzaria.service;

import com.uniamerica.pizzaria.entity.*;
import com.uniamerica.pizzaria.repository.AtendenteRep;
import com.uniamerica.pizzaria.repository.ClienteRep;
import com.uniamerica.pizzaria.repository.PedidoRep;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        BigDecimal totalValorPedidos = encerrado.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);



        return  ResponseEntity.ok("Total de pedidos:" + pedidosDoDia + "\n Total de pedidos encerrados: " + pedidosEncerrados +"Total de pedidos cancelados: "+ pedidosCancelados + "\n Pedidos entregues:" + pedidosEntregues + "\n Pedidos  retirados: "+ pedidosRetirados + "Faturamento total:"+ totalValorPedidos+"\n Pedidos pagos em dinheiro :" + pedidosDinheiro + "\n Pedidos pagos no cartão:" + pedidosCartao);

    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<?> encerrar(Long id) { pedidoRep.findById(id);

        Pedido pedidoAtual = pedidoRep.getById(id);
        pedidoAtual.setStatus(Status.Encerrado);

        BigDecimal ValorPizzas = pedidoAtual.getPizzas().stream().map(Pizza::getValorUnit).reduce(BigDecimal.ZERO, BigDecimal::add);

        pedidoAtual.setValorTotal(ValorPizzas);

        pedidoRep.save(pedidoAtual);


        salvarPedidoEncerrado(pedidoAtual);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoAtual);

    }
    @Transactional(rollbackOn = Exception.class)
    public void salvarPedidoEncerrado(Pedido pedido) {
        String pasta = "C:\\Users\\Lenovo\\Documents\\desenvolvimento\\pizzaria\\Pedidos Encerrados\\";
        String arquivo = pasta + "pedido_" + pedido.getId() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write("Cliente: " );
            writer.newLine();
            writer.write("Endereço: " );
            writer.newLine();
            writer.write("Itens do pedido:");
            writer.newLine();
            for (Pizza item : pedido.getPizzas()) {
                writer.write(" - " + item );
                writer.newLine();
            }
            writer.write("Total do pedido: R$ " + pedido.getValorTotal());
            writer.newLine();
            System.out.println("Arquivo gerado com sucesso: " + arquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }

    }


}
