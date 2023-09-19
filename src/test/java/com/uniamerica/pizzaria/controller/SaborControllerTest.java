package com.uniamerica.pizzaria.controller;
import com.uniamerica.pizzaria.entity.Sabor;
import com.uniamerica.pizzaria.repository.PizzaRep;
import com.uniamerica.pizzaria.repository.SaborRep;
import com.uniamerica.pizzaria.service.SaborService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;



@SpringBootTest
class SaborControllerTest {

    @MockBean
    SaborRep saborRep;

    @MockBean
    PizzaRep pizzaRep;

    @Autowired
    private final SaborService saborService = new SaborService();

    @Autowired
    private  final SaborController saborController = new SaborController();

    @BeforeEach
    void injectFindByAll(){
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);
        List<Sabor> sabores = new ArrayList<>();

        sabores.add(sabor);
        Mockito.when(saborRep.findAll()).thenReturn(sabores);

    }
    @BeforeEach
    void injectInserir(){
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);
        List<Sabor> sabores = new ArrayList<>();

        sabores.add(sabor);
        Mockito.when(saborRep.save(sabor)).thenReturn(sabor);
    }
    @BeforeEach
    void injectUpdate(){
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");
        Sabor sabor = new Sabor(1L,"Calabresa", ingredientes);

        Mockito.when(saborRep.getById(1L)).thenReturn(sabor);
    }

    @Test
    void getAll() {
        var saborcontroller = saborController.getAll();
        List<Sabor> sabores = (List<Sabor>) saborcontroller.getBody();
        assert sabores != null;
        int valor = sabores.size();
        System.out.println(valor);
        Assertions.assertEquals(1, valor, 0);
    }

    @Test
    void inserir() {
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa");

        Sabor sabor = new Sabor( 1,"Calabresa", ingredientes);
        var sabor1 = saborController.inserir(sabor);


        Assertions.assertNotNull(sabor1);
        Assertions.assertEquals("Sabor cadastrado com sucesso!", sabor1.getBody());
    }

    @Test
    void updateSabor() {


        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Calabresa com Alho");


        Sabor sabornovo = new Sabor( "Calabresa com alho", ingredientes);

        var sabor1 = saborController.updateSabor(1L,sabornovo);


        Assertions.assertNotNull(sabor1);
        Assertions.assertEquals("sabor atualizado com sucesso!", sabor1.getBody());
    }

    @Test
    void delete() {
        inserir();

        var delete = saborController.delete(1L);

        Assertions.assertNotNull(delete);
        Assertions.assertEquals("Sabor deletado com sucesso!", delete.getBody());

    }
}