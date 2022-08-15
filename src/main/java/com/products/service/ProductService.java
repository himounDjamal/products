package com.products.service;

import com.products.mapper.ProductMapper;
import com.products.model.Characteristic;
import com.products.model.Product;
import com.products.repository.CharacteristicEntity;
import com.products.repository.CharacteristicRepository;
import com.products.repository.ProductEntity;
import com.products.repository.ProductRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository ;
    @Autowired
    CharacteristicRepository characteristicRepository;

    @Autowired
    ProductMapper mapper;
    public Product get(Long id){
        Optional<ProductEntity> produiteEntiteO = productRepository.findById(id);
        ProductEntity productEntity = produiteEntiteO.get();
       return mapper.produtEntityToProdut(productEntity);
     //   return mapProduit(produiteEntite);
    }

    private Product mapProduit(ProductEntity productEntity) {
        Product pr = new Product();
        pr.setId(productEntity.getId());
        pr.setNom(productEntity.getNom());
        pr.setType(productEntity.getType());
        return pr;
    }

    public Product save(Product product) {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setNom(product.getNom());
        productEntity.setType(product.getType());
     //   if ((product.getCharacteristics()!= null)){}
        List<CharacteristicEntity> characteristicsE = mapChars( product.getCharacteristics());
        productEntity.setCharacteristics(characteristicsE);
        characteristicRepository.saveAll(characteristicsE);
        ProductEntity savedPE = productRepository.save(productEntity);
        return mapper.produtEntityToProdut(savedPE);
    }

    private List<CharacteristicEntity> mapChars(List<Characteristic> characteristics) {
        if (characteristics == null) return new ArrayList<>();

         return  characteristics.stream().map(this::mapChar).toList();
    }

    private CharacteristicEntity mapChar(Characteristic characteristic) {
        CharacteristicEntity d = new CharacteristicEntity();
        d.setId(characteristic.getId());
        d.setName(characteristic.getName());
        d.setLabel(characteristic.getLabel());
        return d;
    }

    public List<Product> getAll() {
      return  productRepository.findAll()
                .stream()
                .map(productEntity -> mapper.produtEntityToProdut(productEntity))
                .toList();
    }

    public String delete(Long id) {
        productRepository.deleteById(id);
        return "item deleted";
    }

    public Product update(Long id, Product prd) {
        Optional<ProductEntity> produiteEntiteO = productRepository.findById(id);
        ProductEntity productEntity = produiteEntiteO.get();
        productEntity.setNom(prd.getNom());
        productEntity.setType(prd.getType());
        ProductEntity saved = productRepository.save(productEntity);
        return mapper.produtEntityToProdut(saved);
    }
}
