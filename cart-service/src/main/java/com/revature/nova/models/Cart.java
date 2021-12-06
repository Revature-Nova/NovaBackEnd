package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "carts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "productList"})
@Getter @Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @Column(name = "date_time")
    private String dateTime;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "cartList", cascade = CascadeType.ALL)
    private List<Product> productList;

    public Cart(){
        productList = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateTime = formatter.format(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Cart {\\n" +
                "  user: " + user + ",\\n" +
                "  productList: " + productList + ",\\n" +
                '}';
    }
}

