package com.uniamerica.pizzaria.repository;

import com.uniamerica.pizzaria.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRep extends JpaRepository<Pedido,Long> {
}
