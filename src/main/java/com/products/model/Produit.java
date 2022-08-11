package com.products.model;

import java.io.Serializable;

public class Produit implements Serializable {
    private Long id;
    private String nom;
    private String type;
    private PointFort pointFort;

    public Produit() {
    }

    public Produit(long id, String nom, String type, PointFort pointFort) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.pointFort = pointFort;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPointFort(PointFort pointFort) {
        this.pointFort = pointFort;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public PointFort getPointFort() {return pointFort;}
}