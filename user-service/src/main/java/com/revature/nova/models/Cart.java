package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "productList"})
@NoArgsConstructor(onConstructor = @__(@Autowired))
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Cart {
    private Integer cartId;

    private List<Product> productList;

    @PostConstruct
    private void init(){
        productList = new ArrayList<>();
    }
}
