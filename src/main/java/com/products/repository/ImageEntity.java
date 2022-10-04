package com.products.repository;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@NoArgsConstructor
@Entity
public class ImageEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id ;
    private String nom ;
    private String url ;

    public void setUrl( long productId,String fileName) {
        this.url = "http://localhost:8080/image/"+ String.valueOf(productId)+"/"+fileName;
    }
}
