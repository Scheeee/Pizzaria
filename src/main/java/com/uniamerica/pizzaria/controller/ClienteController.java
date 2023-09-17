package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.dto.ClienteDTO;

import com.uniamerica.pizzaria.entity.Cliente;
import com.uniamerica.pizzaria.repository.ClienteRep;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/pizzaria/cliente")
public class ClienteController {

    @Autowired
    ClienteRep clienteRep;
    @GetMapping("/lista")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(clienteRep.findAll());
    }
    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody final ClienteDTO cliente){
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
    public ResponseEntity<?> updateCliente(@PathVariable(value = "id")Long id,@RequestBody @Valid ClienteDTO cliente){

        Cliente clienteNovo = clienteRep.getById(id);

        BeanUtils.copyProperties(cliente, clienteNovo, "id");
        clienteRep.save(clienteNovo);
        return ResponseEntity.ok("Cliente atualizado com sucesso!");

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
        try{
        Cliente cliente = clienteRep.getById(id);


        clienteRep.delete(cliente);
        return ResponseEntity.ok("Cliente deletado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }

    }


}
