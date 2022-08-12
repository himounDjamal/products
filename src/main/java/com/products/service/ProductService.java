package com.products.service;

import com.products.model.Produit;
import com.products.repository.ProductRepository;
import com.products.repository.ProduiteEntite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository ;
    public Produit get(Long id){
        Optional<ProduiteEntite> produiteEntiteO = productRepository.findById(id);
        ProduiteEntite produiteEntite = produiteEntiteO.get();
        return mapProduit(produiteEntite);
    }

    private  Produit mapProduit(ProduiteEntite produiteEntite) {
        Produit pr = new Produit();
        pr.setId(produiteEntite.getId());
        pr.setNom(produiteEntite.getNom());
        pr.setType(produiteEntite.getType());
        return pr;
    }

    public Produit save(Produit produit) {
        ProduiteEntite produiteEntite = new ProduiteEntite();
        produiteEntite.setNom(produit.getNom());
        produiteEntite.setType(produit.getType());

        ProduiteEntite savedPE = productRepository.save(produiteEntite);

        return mapProduit(savedPE);
    }

    public List<Produit> getAll() {
      return  productRepository.findAll()
                .stream()
                .map(this::mapProduit)
                .toList();
    }

    public String delete(Long id) {
        productRepository.deleteById(id);
        return "item deleted";
    }

    public Produit update(Long id, Produit prd) {
        Optional<ProduiteEntite> produiteEntiteO = productRepository.findById(id);
        ProduiteEntite produiteEntite = produiteEntiteO.get();
        produiteEntite.setNom(prd.getNom());
        produiteEntite.setType(prd.getType());
        ProduiteEntite saved = productRepository.save(produiteEntite);
        return mapProduit(saved);
    }
}
