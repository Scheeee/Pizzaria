package com.uniamerica.pizzaria.service;

import com.uniamerica.pizzaria.entity.*;
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
    public String totais(String data) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(data, formatter);

        List<Pedido> hoje = pedidoRep.findByCadastrado(date);
        int pedidosDoDia = hoje.size();
        List<Pedido> encerrado = pedidoRep.findByStatusAndCadastrado(Status.ENCERRADO, date);
        int pedidosEncerrados = encerrado.size();
        List<Pedido> cancelado = pedidoRep.findByStatusAndCadastrado(Status.CANCELADO, date);
        int pedidosCancelados = cancelado.size();
        List<Pedido> entregue = pedidoRep.findByEntregaAndStatusAndCadastrado(true,Status.ENCERRADO, date);
        int pedidosEntregues = entregue.size();
        List<Pedido> retirado = pedidoRep.findByEntregaAndStatusAndCadastrado(false,Status.ENCERRADO, date);
        int pedidosRetirados = retirado.size();
        List<Pedido> dinheiro = pedidoRep.findByStatusAndCadastradoAndDinheiro(Status.ENCERRADO, date, true);
        int pedidosDinheiro = dinheiro.size();
        List<Pedido> cartao = pedidoRep.findByStatusAndCadastradoAndDinheiro(Status.ENCERRADO, date, false);
        int pedidosCartao = cartao.size();

        BigDecimal totalValorPedidos = encerrado.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

        return "Total de pedidos: " + pedidosDoDia +
        "\nTotal de pedidos encerrados: " + pedidosEncerrados +
                "\nTotal de pedidos cancelados: " + pedidosCancelados +
                "\nPedidos entregues: " + pedidosEntregues +
                "\nPedidos retirados: " + pedidosRetirados +
                "\nFaturamento total: " + totalValorPedidos +
                "\nPedidos pagos em dinheiro: " + pedidosDinheiro +
                "\nPedidos pagos no cartão: " + pedidosCartao;

      //  return  ResponseEntity.ok("Total de pedidos:" + pedidosDoDia + "\n Total de pedidos encerrados: " + pedidosEncerrados +"Total de pedidos cancelados: "+ pedidosCancelados + "\n Pedidos entregues:" + pedidosEntregues + "\n Pedidos  retirados: "+ pedidosRetirados + "Faturamento total:"+ totalValorPedidos+"\n Pedidos pagos em dinheiro :" + pedidosDinheiro + "\n Pedidos pagos no cartão:" + pedidosCartao);

    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<Object> encerrar(Long id) { pedidoRep.findById(id);

        Pedido pedidoAtual = pedidoRep.getReferenceById(id);
        pedidoAtual.setStatus(Status.ENCERRADO);

        BigDecimal valorPizzas = pedidoAtual.getPizzas().stream().map(Pizza::getValorUnit).reduce(BigDecimal.ZERO, BigDecimal::add);

        pedidoAtual.setValorTotal(valorPizzas);

        pedidoRep.save(pedidoAtual);


        salvarPedidoEncerrado(pedidoAtual);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoAtual);

    }
    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<Object> salvarPedidoEncerrado(Pedido pedido) {
        String pasta = "C:\\Users\\Lenovo\\Documents\\desenvolvimento\\pizzaria\\Pedidos Encerrados\\";
        String arquivo = pasta + "pedido_" + pedido.getId() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write("Cliente: " + pedido.getCliente().getNome());
            writer.newLine();
            writer.write("Telefone: " + pedido.getCliente().getTelefone() );
            writer.newLine();
            if(pedido.isEntrega()) {
                writer.write("Entrega " );
                writer.newLine();
                writer.write("Endereço: " + pedido.getCliente().getEndereco().getRua() + " N°:" + pedido.getCliente().getEndereco().getNumero());
                writer.newLine();
                writer.write("Complemento: " + pedido.getCliente().getEndereco().getComplemento());
                writer.newLine();
            }
            else{
                writer.write("Retirada no balcão " );
                writer.newLine();
            }
            writer.write("Itens do pedido:");
            writer.newLine();
            int nPizza = 1;
            for (Pizza pizza : pedido.getPizzas()) {
                writer.write("- pizza n°: "+ nPizza +" Tamanho:"+ pizza.getTamanho() );
                writer.newLine();
                writer.write("Sabor(es):");
                writer.newLine();
                for(Sabor sabor : pizza.getSabores()){
                    writer.write("-"+ sabor.getNome() );
                    writer.newLine();
                }


                writer.write(" Valor unitário: R$" + pizza.getValorUnit());
                writer.newLine();
                nPizza++;
            }

            if(pedido.isDinheiro()){
                writer.write("Pagamento no Dinheiro");
                writer.newLine();
            }
            else {
                writer.write("Pagamento no Cartão");

            }
            writer.write("Total: R$ " + pedido.getValorTotal());
            writer.newLine();
            System.out.println("Arquivo gerado com sucesso: " + arquivo);
            return ResponseEntity.status(HttpStatus.OK).body("Arquivo gerado com sucesso");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao salvar o arquivo");
        }


    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<String> gerarComanda(Pedido pedido) {
        String pasta = "C:\\Users\\Lenovo\\Documents\\desenvolvimento\\pizzaria\\Comanda\\";
        String arquivo = pasta + "pedido_" + pedido.getId() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write("Atendente: " + pedido.getAtendente().getNome());
            writer.newLine();
            if(pedido.isEntrega()){
                writer.write("Preparar pedido para entrega");
                writer.newLine();
            }
            else{
                writer.write("Retirada no balcão");
                writer.newLine();
            }
           writer.write("Itens do pedido:");
            writer.newLine();
            int nPizza = 1;
            for (Pizza pizza : pedido.getPizzas()) {
                writer.write("- pizza n°: "+ nPizza +" Tamanho:"+ pizza.getTamanho() );
                writer.newLine();
                writer.write("Sabor(es):");
                writer.newLine();
                for(Sabor sabor : pizza.getSabores()){
                    writer.write("-"+ sabor.getNome() );
                    writer.newLine();
                }

                nPizza++;
            }



            System.out.println("Arquivo gerado com sucesso: " + arquivo);
            return ResponseEntity.status(HttpStatus.OK).body("Arquivo gerado com sucesso: " + arquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao salvar o arquivo");
        }

    }




}
