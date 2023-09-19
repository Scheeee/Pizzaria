package com.uniamerica.pizzaria.dto;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@NoArgsConstructor
public class SaborDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    private String nome;

    private List<String> ingredientes;


    public SaborDTO(long i, String calabresa, List<String> calabresa1) {
    }


}
