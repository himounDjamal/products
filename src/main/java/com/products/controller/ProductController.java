package com.products.controller;

import com.products.model.Produit;
import com.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService ;
    List<Produit> products = new ArrayList<>();
    List<Integer> scoreList = new ArrayList<>();

    @GetMapping("/product")
    public List<Produit> products() {
       return productService.getAll();
    }

    @GetMapping("/product/{id}")
    public Produit product(@PathVariable Long id) {
      return   productService.get(id);
    }

    @PostMapping("/product")
    public Produit addProduct(@RequestBody Produit item) {
        return productService.save(item);

    }


    @PutMapping("/product/{id}")
    public Produit updateProduct(@RequestBody Produit item ,@PathVariable(value = "id") Long id) {
        return productService.update(id,item);
        /*for (Produit product : products) {
            if (product.getId().equals(id)) {
                product.setId(item.getId());
                product.setNom(item.getNom());
                product.setType(item.getType());
                product.setPointFort(item.getPointFort());
                updated = product;
            }
        }
*/
       // System.out.println("n'exist pas");

    }

    public String tUpdate(List<Produit> produit, Integer id) {
        System.out.println("here");
        return "false";
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable Long id) {
     return productService.delete(id);
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
