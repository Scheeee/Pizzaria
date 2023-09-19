package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.dto.EnderecoDTO;
import com.uniamerica.pizzaria.entity.Endereco;
import com.uniamerica.pizzaria.repository.EnderecoRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/pizzaria/endereco")
public class EnderecoController {
    @Autowired
    EnderecoRep enderecoRep;
    private static final String ERRO = "Error:  \" + e.getMessage()";

    @GetMapping("/lista")
    public ResponseEntity<List<Endereco>> getAll(){
        return ResponseEntity.ok(enderecoRep.findAll());
    }
    @PostMapping
    public ResponseEntity<Object> inserir(@RequestBody final EnderecoDTO endereco){
        try {
            Endereco endereco1 = new Endereco();
            BeanUtils.copyProperties(endereco,endereco1);
            enderecoRep.save(endereco1);
            return ResponseEntity.ok("Endereço cadastrado com sucesso!");

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(ERRO);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEndereco(@PathVariable(value = "id")Long id,@RequestBody EnderecoDTO endereco){

        Endereco enderecoNovo = enderecoRep.getReferenceById(id);

        BeanUtils.copyProperties(endereco, enderecoNovo, "id");
        enderecoRep.save(enderecoNovo);
        return ResponseEntity.ok("endereço atualizado com sucesso!");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id")Long id){
        try{
        Endereco endereco = enderecoRep.getReferenceById(id);


        enderecoRep.delete(endereco);
        return ResponseEntity.ok("Endereço deletado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ERRO);
        }
    }
}
