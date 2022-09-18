package com.products.repository;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "Products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String type;
    private String img;
   @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<CharacteristicEntity> characteristics;
  //  @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    //private Url url;
}