package com.uniamerica.pizzaria.controller;


import com.uniamerica.pizzaria.dto.SaborDTO;


import com.uniamerica.pizzaria.entity.Sabor;
import com.uniamerica.pizzaria.repository.SaborRep;
import com.uniamerica.pizzaria.service.SaborService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/pizzaria/sabor")
public class SaborController {
    @Autowired
    SaborRep saborRep;
    @Autowired
    SaborService saborService;

    @GetMapping("/lista")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(saborRep.findAll());
    }
    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody final SaborDTO sabor){
        try {
            Sabor sabor1 = new Sabor();
            BeanUtils.copyProperties(sabor,sabor1);
            saborService.saveSabor(sabor1);
            return ResponseEntity.ok("Sabor cadastrado com sucesso!");

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSabor(@PathVariable(value = "id")Long id,@RequestBody @Valid SaborDTO sabor){

        Sabor saborNovo = saborRep.getById(id);

        BeanUtils.copyProperties(sabor, saborNovo, "id");
        saborRep.save(saborNovo);
        return ResponseEntity.ok("sabor atualizado com sucesso!");

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
        try{
            Sabor sabor = saborRep.getById(id);


           saborRep.delete(sabor);
            return ResponseEntity.ok("Sabor deletado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
