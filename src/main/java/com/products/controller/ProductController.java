package com.products.controller;

import com.products.model.Produit;
import com.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService ;
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

    }

    public String tUpdate(List<Produit> produit, Integer id) {
        System.out.println("here");
        return "false";
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable Long id) {
     return productService.delete(id);
    }

}
