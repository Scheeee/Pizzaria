package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.entity.Cliente;
import com.uniamerica.pizzaria.entity.Endereco;
import com.uniamerica.pizzaria.repository.EnderecoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/pizzaria/endereco")
public class EnderecoController {
    @Autowired
    EnderecoRep enderecoRep;

    @GetMapping("/lista")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(enderecoRep.findAll());
    }
    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody final Endereco endereco){
        try {

            enderecoRep.save(endereco);
            return ResponseEntity.ok("Endereço cadastrado com sucesso!");

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Endereco endereco){
        try {
            final Endereco endereco1 = this.enderecoRep.findById(id).orElse(null);

            if (endereco1 == null || endereco1.equals(endereco.getId())){
                throw new RuntimeException("Não foi possivel indentificar o endereço informada");
            }
            this.enderecoRep.save(endereco);
            return ResponseEntity.ok
                    ("Endereço cadastrado(a) com sucesso");
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
