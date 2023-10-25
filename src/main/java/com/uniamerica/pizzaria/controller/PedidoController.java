package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.dto.PedidoDTO;
import com.uniamerica.pizzaria.entity.*;
import com.uniamerica.pizzaria.repository.AtendenteRep;
import com.uniamerica.pizzaria.repository.ClienteRep;
import com.uniamerica.pizzaria.repository.PedidoRep;
import com.uniamerica.pizzaria.service.PedidoService;
import org.modelmapper.ModelMapper;
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
    private static final String ERRO = "Error:  ";

    @GetMapping("/data/{data}")
    public ResponseEntity<Total> findByData(@PathVariable("data") String dataString){
            return ResponseEntity.ok(pedidoService.totais(dataString));
    }
    @GetMapping("/atendente/{id}")
    public ResponseEntity<Optional<List<Pedido>>> getatendente(@PathVariable("id") Long id){


        Optional<List<Pedido>> pedidos = this.pedidoRep.findByAtendente(atendenteRep.getReferenceById(id));
        return ResponseEntity.ok(pedidos);
    }
    @GetMapping("/pedido/cliente/{id}")
    public ResponseEntity<Optional<List<Pedido>>> getClientePedido(@PathVariable("id") Long id){
        Optional<List<Pedido>> pedidos = this.pedidoRep.findByCliente(clienteRep.getReferenceById(id));
        return ResponseEntity.ok(pedidos);
    }
    @GetMapping("/cliente/{id}")
    public ResponseEntity<Optional<Cliente>> getCliente(@PathVariable("id") Long id){
      Optional<Cliente> cliente = Optional.of(this.clienteRep.getReferenceById(id));
      return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pedido>> findById(@PathVariable("id") Long id) {
        Optional<Pedido> pessoa = this.pedidoRep.findById(id);
        return ResponseEntity.ok(pessoa);
    }
    @GetMapping("/comanda/{id}/{atendente}")
    public ResponseEntity<String> comanda(@PathVariable("id") Long id,@PathVariable("atendente") Long idAtendente){
        try {
            Pedido pedido = pedidoRep.getReferenceById(id);
          Atendente atendente = atendenteRep.getReferenceById(idAtendente);
            Assert.isTrue(pedido.getStatus() == Status.ATIVO, "o pedido solicitado não está ativo");
            Assert.isTrue(pedido.getPizzas().size() >= 1 , "o pedido solicitado não possui pizzas");

            pedidoService.gerarComanda(pedido,atendente);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ERRO);
        }
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Pedido>> getativos(){
        return ResponseEntity.ok(pedidoRep.findByStatus(Status.ATIVO));
    }
    @GetMapping("/finalizados")
    public ResponseEntity<List<Pedido>> getfinalizados(){
      return ResponseEntity.ok(pedidoRep.findByStatus(Status.ENCERRADO));
    }
    @GetMapping("/cancelados")
    public ResponseEntity<List<Pedido>> getcancelados(){
      return ResponseEntity.ok(pedidoRep.findByStatus(Status.CANCELADO));
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Pedido>> getAll(){
        return ResponseEntity.ok(pedidoRep.findAll());
    }
    @PostMapping
    public ResponseEntity<Object> inserir(@RequestBody final PedidoDTO pedido){
        try {

            ModelMapper modelMapper = new ModelMapper();
            Pedido pedido1 =  modelMapper.map(pedido, Pedido.class);
            BeanUtils.copyProperties(pedido,pedido1);
           pedidoRep.save(pedido1);
          return new ResponseEntity<>(HttpStatus.OK);

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(ERRO+ e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePedido(@PathVariable(value = "id")Long id,@RequestBody PedidoDTO pedido){
      try {
        Pedido pedidoNovo = pedidoRep.getReferenceById(id);

        BeanUtils.copyProperties(pedido, pedidoNovo, "id");
        pedidoRep.save(pedidoNovo);
        return new ResponseEntity<>(HttpStatus.OK);

      }
      catch (Exception e ){
        return ResponseEntity.internalServerError().body(ERRO);
      }

    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Object> cancelarPedido(@PathVariable(value = "id") Long id){
        pedidoRep.findById(id);

        Pedido pedidoAtual = pedidoRep.getReferenceById(id);
        pedidoAtual.setStatus(Status.CANCELADO);

        pedidoRep.save(pedidoAtual);
        return new ResponseEntity<>(HttpStatus.OK);


    }
    @PutMapping("/{id}/encerrar")
    public ResponseEntity<Object> encerrarPedido(@PathVariable(value = "id") Long id){
        try {
             pedidoService.encerrar(id);
          return new ResponseEntity<>(HttpStatus.OK);

        }
        catch (Exception e ){
            return ResponseEntity.internalServerError().body(ERRO);
        }


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id")Long id){
        try{
            Pedido pedido = pedidoRep.getReferenceById(id);


            pedidoRep.delete(pedido);
          return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ERRO+ e.getMessage());
        }
    }


}
