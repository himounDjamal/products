package com.products.controller;

import com.products.model.Produit;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    List<Produit> products = new ArrayList<>();
    List<Integer> scoreList = new ArrayList<>();

    @GetMapping("/product")
    public List<Produit> products() {

        return products;
    }

    @GetMapping("/product/{id}")
    public Produit product(@PathVariable Long id) {
        return products.stream()
                .filter(produit -> produit.getId().equals(id))
                .findFirst()
                .get();
    }

    @PostMapping("/product")
    public Produit addProduct(@RequestBody Produit item) {
        products.add(item);
        return item;
    }


    @PutMapping("/product/{id}")
    public Produit updateProduct(@RequestBody Produit item ,@PathVariable(value = "id") Long id) {
        Produit updated= null;
        for (Produit product : products) {
            if (product.getId().equals(id)) {
                product.setId(item.getId());
                product.setNom(item.getNom());
                product.setType(item.getType());
                product.setPointFort(item.getPointFort());
                updated = product;
            }
        }

       // System.out.println("n'exist pas");
        return updated;
    }

    public String tUpdate(List<Produit> produit, Integer id) {
        System.out.println("here");
        return "false";
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        products.removeIf(produit -> produit.getId().equals(id));
     return"item was deleted";
    }













    @DeleteMapping("/list/{id}")
    public List<Integer> deleteProduct(@PathVariable(value = "id") Integer id) {
        scoreList.add(93);
        scoreList.add(50);
        scoreList.add(90);
        scoreList.remove(id);
        return scoreList;
    }

    @PutMapping("/mlist/{id}")
    public List<Integer> updateItem(@PathVariable(value = "id") Integer id) {
        scoreList.add(93);
        scoreList.add(50);
        scoreList.add(90);
        scoreList.set(1, id);
        return scoreList;
    }

    @PutMapping("/list/{id}")
    public List<Integer> updateItem2(@RequestBody Integer val, @PathVariable(value = "id") Integer id) {
        scoreList.add(93);
        scoreList.add(50);
        scoreList.add(90);
        scoreList.set(id, val);
        return scoreList;
    }

    @GetMapping("/list")
    public List<Integer> listitems() {
        return scoreList;
    }
}
