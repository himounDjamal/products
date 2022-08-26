package com.products.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
public class Product implements Serializable {
    private Long id;
    private String nom;
    private String type;
    private List<Characteristic> characteristics ;
    private List<Link> link ;
}