package com.products.mapper;

import com.products.model.Produit;
import com.products.repository.ProduiteEntite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProduitMapper {

    ProduitMapper INSTANCE = Mappers.getMapper(ProduitMapper.class);

    Produit produitEntiteToProduit(ProduiteEntite produiteEntite);
    ProduiteEntite produitToProduitEntite (Produit produit);

}
