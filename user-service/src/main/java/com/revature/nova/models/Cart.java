package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "productList"})
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor(onConstructor = @__(@Autowired))
public class Cart {
    private Integer cartId;
    private List<Product> productList;
    private String dateTime;

    @PostConstruct
    public void init(){
        productList = new ArrayList<>();
    }
}
