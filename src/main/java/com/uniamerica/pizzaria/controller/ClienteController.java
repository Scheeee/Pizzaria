package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.dto.ClienteDTO;
import com.uniamerica.pizzaria.entity.Atendente;
import com.uniamerica.pizzaria.entity.Cliente;
import com.uniamerica.pizzaria.repository.ClienteRep;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/pizzaria/cliente")
public class ClienteController {


    @Autowired
    ClienteRep clienteRep;
    private static final String ERRO = "Error:  ";
    @GetMapping("/lista")
    public ResponseEntity<List<Cliente>> findAll(){
        try {
            List<Cliente> lista = clienteRep.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
  @GetMapping("/{nome}")
  public ResponseEntity<List<Cliente>> findByNome(@PathVariable("nome") String nome) {
    List<Cliente> clientes = clienteRep.findAll()
      .stream()
      .filter(a -> a.getNome() != null && a.getNome().equalsIgnoreCase(nome))
      .collect(Collectors.toList());

    return ResponseEntity.ok(clientes);
  }
    @PostMapping
    public ResponseEntity<Object> inserir(@RequestBody final ClienteDTO cliente){
        try {
            Assert.notNull(cliente.getNome());
            ModelMapper modelMapper = new ModelMapper();
            Cliente cliente1 = modelMapper.map(cliente, Cliente.class);

            clienteRep.save(cliente1);
            return new ResponseEntity<>(HttpStatus.OK);

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(ERRO+e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable(value = "id")Long id,@RequestBody ClienteDTO cliente){

        Cliente clienteNovo = clienteRep.getReferenceById(id);

        BeanUtils.copyProperties(cliente, clienteNovo, "id");
        clienteRep.save(clienteNovo);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id")Long id){
        try{
        Cliente cliente = clienteRep.getReferenceById(id);


        clienteRep.delete(cliente);
        return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ERRO+ e.getMessage());
        }

    }


}
