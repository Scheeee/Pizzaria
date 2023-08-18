package com.uniamerica.pizzaria.repository;

import com.uniamerica.pizzaria.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRep extends JpaRepository<Pedido,Long> {

    List<Pedido> findByCadastrado(LocalDate data);
}
