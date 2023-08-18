package com.uniamerica.pizzaria.repository;

import com.uniamerica.pizzaria.entity.Pedido;
import com.uniamerica.pizzaria.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRep extends JpaRepository<Pedido,Long> {

    List<Pedido> findByCadastrado(LocalDate data);




    List<Pedido> findByStatusAndCadastrado(Status encerrado, LocalDate date);

    List<Pedido> findByEntregaAndStatusAndCadastrado(boolean b, Status encerrado, LocalDate date);

    List<Pedido> findByStatusAndCadastradoAndDinheiro(Status encerrado, LocalDate date, boolean b);

    List<Pedido> findByStatus(Status ativo);
}
