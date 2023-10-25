package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.dto.ProdutoDTO;
import com.uniamerica.pizzaria.entity.Pedido;
import com.uniamerica.pizzaria.entity.Produto;
import com.uniamerica.pizzaria.repository.ProdutoRep;
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

@Controller
@RequestMapping(value = "/pizzaria/produto")
public class ProdutoController {

  @Autowired
  ProdutoRep produtoRep;

  private static final String ERRO = "Error: ";
  @GetMapping("/lista")
  public ResponseEntity<List<Produto>> findAll(){
    return ResponseEntity.ok(produtoRep.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Produto>> findById(@PathVariable("id") Long id) {
    Optional<Produto> produto = this.produtoRep.findById(id);
    return ResponseEntity.ok(produto);
  }
  @PostMapping
  public ResponseEntity<Object> inserir(@RequestBody final ProdutoDTO produto){
    try {
      Assert.notNull(produto.getNome());
      ModelMapper modelMapper = new ModelMapper();
      Produto produto1 = modelMapper.map(produto, Produto.class);

      produtoRep.save(produto1);
      return new ResponseEntity<>(HttpStatus.OK);

    }
    catch (Exception e){
      return ResponseEntity.internalServerError().body(ERRO+e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateProduto(@PathVariable(value = "id") long id, @RequestBody ProdutoDTO produto){

    Produto produtoNovo = produtoRep.getReferenceById(id);
    BeanUtils.copyProperties(produto, produtoNovo, "id", "cadastro");
    produtoRep.save(produtoNovo);
    return new ResponseEntity<>(HttpStatus.OK);

  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable(value = "id")Long id){
    try {
      Produto produto = produtoRep.getReferenceById(id);


      produtoRep.delete(produto);
      return new ResponseEntity<>(HttpStatus.OK);
    }catch (Exception e){
      return ResponseEntity.internalServerError().body(ERRO+e.getMessage());
    }

  }
}
