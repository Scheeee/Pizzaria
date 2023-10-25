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

    String mensagem = "Arquivo gerado com sucesso: ";
    @Transactional(rollbackOn = Exception.class)
    public Total totais(String data) {


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


      Total total = new Total(pedidosDoDia, pedidosEncerrados, pedidosCancelados, pedidosEntregues, pedidosRetirados,totalValorPedidos,  pedidosDinheiro, pedidosCartao);

      return total;



    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<Object> encerrar(Long id) { pedidoRep.findById(id);

        Pedido pedidoAtual = pedidoRep.getReferenceById(id);
        pedidoAtual.setStatus(Status.ENCERRADO);

        BigDecimal valorPizzas = pedidoAtual.getPizzas().stream().map(Pizza::getValorUnit).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal valorProdutos = pedidoAtual.getProdutos().stream().map(Produto::getValorUnit).reduce(BigDecimal.ZERO,BigDecimal::add );


        pedidoAtual.setValorTotal(valorPizzas.add(valorProdutos));

        pedidoRep.save(pedidoAtual);


        salvarPedidoEncerrado(pedidoAtual);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoAtual);

    }
    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<Object> salvarPedidoEncerrado(Pedido pedido) {
        String pasta = "C:\\Users\\Lenovo\\Documents\\desenvolvimentoFront\\Pizzaria\\pizzaria\\backend\\Pedidos Encerrados\\";
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
            for (Produto produto : pedido.getProdutos()) {
              writer.write("- pizza n°: "+ nPizza +" Detalhes:"+ produto.getDetalhes() );
              writer.newLine();

              writer.write(" Valor unitário: R$" + produto.getValorUnit());
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

            return ResponseEntity.status(HttpStatus.OK).body("Arquivo gerado com sucesso");
        } catch (IOException e) {

            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao salvar o arquivo");
        }


    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<Object> gerarComanda(Pedido pedido, Atendente atendente) {
        String pasta = "C:\\Users\\Lenovo\\Documents\\desenvolvimentoFront\\Pizzaria\\pizzaria\\backend\\Comanda\\";
        String arquivo = pasta + "pedido_" + pedido.getId() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write("Atendente: " + atendente.getNome());
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
            for (Produto produto : pedido.getProdutos()) {
              writer.write("- produto n°: "+ nPizza +" Detalhes:"+ produto.getDetalhes() );
              writer.newLine();
              nPizza++;
            }


            pedido.setAtendente(atendente);

            return ResponseEntity.status(HttpStatus.OK).body(mensagem + arquivo);
        } catch (IOException e) {

            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao salvar o arquivo");
        }

    }




}
