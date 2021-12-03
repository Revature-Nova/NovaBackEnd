package com.revature.nova.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * This is a Model for a product to be displayed in the storefront.
 */

@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "cartList"})
@NoArgsConstructor(onConstructor = @__(@Autowired))
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Product {
    private Integer productId;

    @NonNull
    private String title;

    @NonNull
    private String genre;

    @NonNull
    private Float price;

    @NonNull
    private String rating;

    @NonNull
    private String endpoint;

    @NonNull
    private String platform;

    @NonNull
    private String imageUrl;

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
                '}';
    }
}
