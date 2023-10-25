package com.uniamerica.pizzaria.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class Total {

  int pedidosDoDia;
  int  pedidosEncerrados;
  int pedidosCancelados;
  int pedidosEntregues;
  int  pedidosRetirados;
  BigDecimal totalValorPedidos;
  int pedidosDinheiro;
  int pedidosCartao;



  public Total(int pedidosDoDia, int pedidosEncerrados, int pedidosCancelados, int pedidosEntregues, int pedidosRetirados, BigDecimal totalValorPedidos, int pedidosDinheiro, int pedidosCartao) {
    this.pedidosDoDia = pedidosDoDia;
    this.pedidosEncerrados = pedidosEncerrados;
    this.pedidosCancelados = pedidosCancelados;
    this.pedidosEntregues = pedidosEntregues;
    this.pedidosRetirados = pedidosRetirados;
    this.totalValorPedidos = totalValorPedidos;
    this.pedidosDinheiro = pedidosDinheiro;
    this.pedidosCartao = pedidosCartao;
  }
}
