package com.products.controller;

import com.products.model.Characteristic;
import com.products.model.Product;
import com.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin

public class ProductController {
    @Autowired
    ProductService productService ;
    @GetMapping("/product")
    public List<Product> products() {
       return productService.getAll();
    }

    @GetMapping("/product/{productid}")
    public Product product(@PathVariable Long productid) {
      return   productService.get(productid);
    }

    @GetMapping("/product/{productid}/characteristic")
    public List<Characteristic> getProductCharacteristics(@PathVariable Long productid){
        return productService.getProductCharacteristics(productid);
    }
    @GetMapping("/product/{productid}/characteristic/{characteristicId}")
    public  Characteristic getProductCharacteristic (@PathVariable Long productid,@PathVariable Long characteristicId){
        return productService.productCharacteristic(productid,characteristicId);
    }
    @PostMapping("/product")
    public Product addProduct(@RequestBody Product item) {
        return productService.save(item);
    }
     @PutMapping("/product/{productid}/characteristic")
     public Product addcharacteristic(@RequestBody List<Characteristic> characteristic, @PathVariable Long productid ){
        return productService.addCharacteristic(productid,characteristic);
     }
    @PutMapping("/product/{productid}")
    public Product updateProduct(@RequestBody Product product , @PathVariable Long productid) {
        return productService.update(productid,product);
    }
    @DeleteMapping("/product/{productid}")
    public String deleteProduct(@PathVariable Long id) {
        return productService.delete(id);
}
    @DeleteMapping("/product/{productid}/characteristic")
    public String deleteProductChartr(@PathVariable Long productid) {
        return productService.deletALLcharacteristic(productid);
    }
    @DeleteMapping("/product/{productid}/characteristic/{id2}")
    public String deleteProductOneChartr(@PathVariable Long productid,@PathVariable Long id2) {
        return productService.deletecharacteristic(productid,id2);
    }
}
