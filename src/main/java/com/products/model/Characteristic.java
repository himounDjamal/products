package com.products.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Characteristic {
    private Long id;
    private String name;
    private String label ;
    private Link link;
}

