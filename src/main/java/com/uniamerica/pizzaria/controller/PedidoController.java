package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.entity.Endereco;
import com.uniamerica.pizzaria.entity.Pedido;
import com.uniamerica.pizzaria.repository.PedidoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/pizzaria/pedido")
public class PedidoController {

    @Autowired
    PedidoRep pedidoRep;

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

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Pedido pedido){
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


}
