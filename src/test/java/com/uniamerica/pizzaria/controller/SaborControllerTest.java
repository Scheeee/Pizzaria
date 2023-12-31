package com.uniamerica.pizzaria.controller;
import com.uniamerica.pizzaria.dto.SaborDTO;
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

        Sabor sabor = new Sabor(1L,"Calabresa", "Calabresa");
        List<Sabor> sabores = new ArrayList<>();

        sabores.add(sabor);
        Mockito.when(saborRep.findAll()).thenReturn(sabores);

    }
    @BeforeEach
    void injectInserir(){

        Sabor sabor = new Sabor(1L,"Calabresa", "Calabresa");
        List<Sabor> sabores = new ArrayList<>();

        sabores.add(sabor);
        Mockito.when(saborRep.save(sabor)).thenReturn(sabor);
    }
    @BeforeEach
    void injectUpdate(){

        Sabor sabor = new Sabor(1L,"Calabresa", "Calabresa");

        Mockito.when(saborRep.getReferenceById(1L)).thenReturn(sabor);
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
        String ingredientes = "Calabresa";

        SaborDTO sabor = new SaborDTO( 1,"Calabresa", ingredientes);
        var sabor1 = saborController.inserir(sabor);


        Assertions.assertNotNull(sabor1);
        Assertions.assertEquals(null, sabor1.getBody());
    }
    @Test
    void inserirErro() {

        var sabor1 = saborController.inserir(new SaborDTO());


        Assertions.assertNotNull(sabor1);
        Assertions.assertEquals(null, sabor1.getBody());
    }

    @Test
    void updateSabor() {





        SaborDTO sabornovo = new SaborDTO( "Calabresa com alho", "Calabresa, Alho");

        var sabor1 = saborController.updateSabor(1L,sabornovo);


        Assertions.assertNotNull(sabor1);
        Assertions.assertEquals("sabor atualizado com sucesso!", sabor1.getBody());
    }

   /* @Test
    void delete() {
        inserir();

        var delete = saborController.delete(1L);

        Assertions.assertNotNull(delete);
        Assertions.assertEquals(null, delete.getBody());

    }*/
}
