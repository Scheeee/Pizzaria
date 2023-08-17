package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.entity.Atendente;
import com.uniamerica.pizzaria.entity.Cliente;
import com.uniamerica.pizzaria.repository.ClienteRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/pizzaria/cliente")
public class ClienteController {

    @Autowired
    ClienteRep clienteRep;
    @GetMapping("/lista")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(clienteRep.findAll());
    }
    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody final Cliente cliente){
        try {

            clienteRep.save(cliente);
            return ResponseEntity.ok("Cliente cadastrado(a) com sucesso!");

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Cliente cliente){
        try {
            final Cliente cliente1 = this.clienteRep.findById(id).orElse(null);

            if (cliente1 == null || cliente1.equals(cliente.getId())){
                throw new RuntimeException("Não foi possivel indentificar o cliente informada");
            }
            this.clienteRep.save(cliente);
            return ResponseEntity.ok
                    ("Atendente Cadastrado(a) com Sucesso");
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
