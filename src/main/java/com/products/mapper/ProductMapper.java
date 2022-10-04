package com.products.mapper;

import com.products.model.Product;
import com.products.repository.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product produtEntityToProdut(ProductEntity productEntity);
    ProductEntity produtToProdutEntity(Product product);

}
