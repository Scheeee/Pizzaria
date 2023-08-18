package com.uniamerica.pizzaria.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uniamerica.pizzaria.entity.Endereco;
import com.uniamerica.pizzaria.entity.Pedido;
import com.uniamerica.pizzaria.entity.Status;
import com.uniamerica.pizzaria.repository.PedidoRep;
import com.uniamerica.pizzaria.service.PedidoService;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping(value = "/pizzaria/pedido")
public class PedidoController {

    @Autowired
    PedidoRep pedidoRep;
    @Autowired
    PedidoService pedidoService;

    @GetMapping("/{data}")
    public ResponseEntity<?> findById(@PathVariable("data") String dataString){
        try {

            return new ResponseEntity<>(pedidoService.totais(dataString), HttpStatus.OK);

        } catch (Exception e) {
            // Lida com a exceção caso a data não esteja no formato esperado
            return new ResponseEntity<>("Formato de data inválido.", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/ativos")
    public ResponseEntity<?> getativos(){
        return ResponseEntity.ok(pedidoRep.findByStatus(Status.Ativo));
    }
    @GetMapping("/lista")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(pedidoRep.findAll());
    }
    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody final Pedido pedido){
        try {

           pedidoRep.save(pedido);
            return ResponseEntity.ok("pedido cadastrado com sucesso!");

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final Pedido pedido){
        try {
            final Pedido pedido1 = this.pedidoRep.findById(id).orElse(null);

            if (pedido1 == null || pedido1.equals(pedido.getId())){
                throw new RuntimeException("Não foi possivel indentificar o pedido informada");
            }
            this.pedidoRep.save(pedido);
            return ResponseEntity.ok
                    ("pedido cadastrado com sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarPedido(@PathVariable(value = "id") Long id){
        pedidoRep.findById(id);

        Pedido pedidoAtual = pedidoRep.getById(id);
        pedidoAtual.setStatus(Status.Cancelado);

        pedidoRep.save(pedidoAtual);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoAtual);

    }
    @PutMapping("/{id}/encerrar")
    public ResponseEntity<?> EncerrarPedido(@PathVariable(value = "id") Long id){
        try {
            return pedidoService.encerrar(id);
        }
        catch (Exception e ){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }


    }



}
