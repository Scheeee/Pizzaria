package com.uniamerica.pizzaria.service;

import com.uniamerica.pizzaria.entity.Sabor;
import com.uniamerica.pizzaria.repository.SaborRep;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaborService {

    @Autowired
    SaborRep saborRep;

    @Transactional
    public void saveSabor(){

        List<Sabor> sabores = saborRep.findAll();


    }
}
