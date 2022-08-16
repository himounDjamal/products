package com.products.controller;

import com.products.model.Characteristic;
import com.products.model.Product;
import com.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService ;
    @GetMapping("/product")
    public List<Product> products() {
       return productService.getAll();
    }

    @GetMapping("/product/{id}")
    public Product product(@PathVariable Long id) {
      return   productService.get(id);
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product item) {
        return productService.save(item);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@RequestBody Product item , @PathVariable(value = "id") Long id) {
        return productService.update(id,item);
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return productService.delete(id);
}

}
