package com.revature.nova.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;


/**
 * This is a Model for a product to be displayed in the storefront.
 */

@Entity @Table
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "cartList"})
@NoArgsConstructor(onConstructor = @__(@Autowired))
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NonNull @Column
    private String title;

    @NonNull @Column
    private String genre;

    @NonNull @Column
    private Float price;

    @NonNull @Column
    private String rating;

    @NonNull @Column
    private String endpoint;

    @NonNull @Column
    private String platform;

    @NonNull @Column
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cart cartList;

    @Override
    public String toString() {
        return "Product {\n" +
                "productId: " + productId + ",\n" +
                "title: " + title + ",\n" +
                "genre: " + genre + ",\n" +
                "price: " + price + ",\n" +
                "rating: " + rating + ",\n" +
                "endpoint: " + endpoint + ",\n" +
                "platform: " + platform + ",\n" +
                "imageUrl: " + imageUrl + ",\n" +
                "cartList: " + cartList + ",\n" +
                '}';
    }
}
