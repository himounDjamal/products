package com.products.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Image {
    private long id ;
    private String nom ;
    private String url ;
   /* public Image(long id, String nom, String url ,long productId,String imageName) {
        this.id = id;
        this.nom = nom;
        this.url = url+String.valueOf(productId)+imageName;
    }*/
}
