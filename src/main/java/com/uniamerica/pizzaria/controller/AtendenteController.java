package com.uniamerica.pizzaria.controller;
import com.uniamerica.pizzaria.dto.AtendenteDTO;
import com.uniamerica.pizzaria.entity.Atendente;
import com.uniamerica.pizzaria.repository.AtendenteRep;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/pizzaria/atendente")
public class AtendenteController {



    @Autowired
    AtendenteRep atendenteRep;


    private static final String ERRO = "Error: ";
    @GetMapping("/lista")
    public ResponseEntity<List<Atendente>> findAll(){
        return ResponseEntity.ok(atendenteRep.findAll());
    }
  @GetMapping("/{nome}")
  public ResponseEntity<List<Atendente>> findByNome(@PathVariable("nome") String nome) {
    List<Atendente> atendentes = atendenteRep.findAll()
      .stream()
      .filter(a -> a.getUsername() != null && a.getUsername().equalsIgnoreCase(nome))
      .collect(Collectors.toList());

    return ResponseEntity.ok(atendentes);
  }

  @PostMapping
    public ResponseEntity<Object> inserir(@RequestBody final AtendenteDTO atendente){
        try {
            Assert.notNull(atendente.getUsername());
            ModelMapper modelMapper = new ModelMapper();
            Atendente atendente1 = modelMapper.map(atendente, Atendente.class);

           /* String encodedPassword = passwordEncoder.encode(atendente1.getPassword());
            atendente1.setPassword(encodedPassword);*/






            atendenteRep.save(atendente1);
          return new ResponseEntity<>(HttpStatus.OK);

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(ERRO+e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAtendente(@PathVariable(value = "id") long id, @RequestBody AtendenteDTO atendente){

        Atendente atendenteNovo = atendenteRep.getReferenceById(id);
        BeanUtils.copyProperties(atendente, atendenteNovo, "id");
        atendenteRep.save(atendenteNovo);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id")Long id){
        try {
            Atendente atendente = atendenteRep.getReferenceById(id);


            atendenteRep.delete(atendente);
          return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
        return ResponseEntity.internalServerError().body(ERRO+e.getMessage());
    }

    }




}
