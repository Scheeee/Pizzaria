package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.entity.Pedido;
import com.uniamerica.pizzaria.entity.Status;
import com.uniamerica.pizzaria.repository.AtendenteRep;
import com.uniamerica.pizzaria.repository.ClienteRep;
import com.uniamerica.pizzaria.repository.PedidoRep;
import com.uniamerica.pizzaria.service.PedidoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(value = "/pizzaria/pedido")
public class PedidoController {

    @Autowired
    PedidoRep pedidoRep;
    @Autowired
    PedidoService pedidoService;
    @Autowired
    private ClienteRep clienteRep;
    @Autowired
    private AtendenteRep atendenteRep;

    @GetMapping("/{data}")
    public ResponseEntity<String> findByData(@PathVariable("data") String dataString){
        try {
            return ResponseEntity.ok(pedidoService.totais(dataString));

        } catch (Exception e) {
            return new ResponseEntity<>("Formato de data inválido.", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/atendente/{id}")
    public ResponseEntity<Optional<List<Pedido>>> getatendente(@PathVariable("id") Long id){


        Optional<List<Pedido>> pedidos = this.pedidoRep.findByAtendente(atendenteRep.getReferenceById(id));
        return ResponseEntity.ok(pedidos);
    }
    @GetMapping("/cliente/{id}")
    public ResponseEntity<Optional<List<Pedido>>> getCliente(@PathVariable("id") Long id){
        Optional<List<Pedido>> pedidos = this.pedidoRep.findByCliente(clienteRep.getReferenceById(id));
        return ResponseEntity.ok(pedidos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pedido>> findById(@PathVariable("id") Long id) {
        Optional<Pedido> pessoa = this.pedidoRep.findById(id);
        return ResponseEntity.ok(pessoa);
    }
    @GetMapping("/comanda/{id}")
    public ResponseEntity<?> comanda(@PathVariable("id") Long id) {
        try {
            Pedido pedido = pedidoRep.getReferenceById(id);
            Assert.isTrue(pedido.getStatus() == Status.Ativo, "o pedido solicitado não está ativo");
            Assert.isTrue(pedido.getPizzas().size() >= 1 , "o pedido solicitado não possui pizzas");

            pedidoService.gerarComanda(pedido);
            return ResponseEntity.ok("comanda gerada com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Pedido>> getativos(){
        return ResponseEntity.ok(pedidoRep.findByStatus(Status.Ativo));
    }

    @GetMapping("/lista")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(pedidoRep.findAll());
    }
    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody final Pedido pedido){
        try {
            Pedido pedido1 = new Pedido();
            BeanUtils.copyProperties(pedido,pedido1);
           pedidoRep.save(pedido1);
            return ResponseEntity.ok("pedido cadastrado com sucesso!");

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePedido(@PathVariable(value = "id")Long id,@RequestBody Pedido pedido){

        Pedido pedidoNovo = pedidoRep.getReferenceById(id);

        BeanUtils.copyProperties(pedido, pedidoNovo, "id");
        pedidoRep.save(pedidoNovo);
        return ResponseEntity.ok("pedido atualizado com sucesso!");

    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarPedido(@PathVariable(value = "id") Long id){
        pedidoRep.findById(id);

        Pedido pedidoAtual = pedidoRep.getReferenceById(id);
        pedidoAtual.setStatus(Status.Cancelado);

        pedidoRep.save(pedidoAtual);
        return ResponseEntity.ok("pedido cancelado com sucesso!");


    }
    @PutMapping("/{id}/encerrar")
    public ResponseEntity<?> EncerrarPedido(@PathVariable(value = "id") Long id){
        try {
             pedidoService.encerrar(id);
            return ResponseEntity.ok("pedido encerrado com sucesso!");

        }
        catch (Exception e ){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
        try{
            Pedido pedido = pedidoRep.getReferenceById(id);


            pedidoRep.delete(pedido);
            return ResponseEntity.ok("Pedido deletado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }


}
