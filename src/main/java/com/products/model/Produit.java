package com.products.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class Produit implements Serializable {
    private Long id;
    private String nom;
    private String type;
    private PointFort pointFort;
}