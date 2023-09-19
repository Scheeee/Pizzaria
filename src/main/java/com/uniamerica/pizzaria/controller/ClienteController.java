package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.entity.Cliente;
import com.uniamerica.pizzaria.repository.ClienteRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/pizzaria/cliente")
public class ClienteController {

    @Autowired
    ClienteRep clienteRep;
    @GetMapping("/lista")
    public ResponseEntity<List<Cliente>> findAll(){
        return ResponseEntity.ok(clienteRep.findAll());
    }
    @PostMapping
    public ResponseEntity<Object> inserir(@RequestBody final Cliente cliente){
        try {
            Cliente cliente1 = new Cliente();
            BeanUtils.copyProperties(cliente,cliente1);
            clienteRep.save(cliente1);
            return ResponseEntity.ok("Cliente cadastrado(a) com sucesso!");

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable(value = "id")Long id,@RequestBody Cliente cliente){

        Cliente clienteNovo = clienteRep.getReferenceById(id);

        BeanUtils.copyProperties(cliente, clienteNovo, "id");
        clienteRep.save(clienteNovo);
        return ResponseEntity.ok("Cliente atualizado com sucesso!");

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id")Long id){
        try{
        Cliente cliente = clienteRep.getReferenceById(id);


        clienteRep.delete(cliente);
        return ResponseEntity.ok("Cliente deletado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }

    }


}
