package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "productList"})
@Getter @Setter
@NoArgsConstructor(onConstructor = @__(@Autowired))
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Cart {
    private Integer cartId;

    @NonNull
    private List<Product> productList;

    @PostConstruct
    private void init(){
        productList = new ArrayList<>();
    }
}
