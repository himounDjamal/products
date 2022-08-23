package com.products.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Link {
    String relation ;
    String url  ;
    public Link(String relation, String url) {
        this.relation = relation;
        this.url = "http://localhost:8080/"+relation+"/"+url;
    }
}

