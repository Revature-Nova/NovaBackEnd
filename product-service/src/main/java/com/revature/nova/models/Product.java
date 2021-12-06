package com.revature.nova.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;


/**
 * This is a Model for a product to be displayed in the storefront.
 */

@Entity
@Table(name = "products")
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "cartList"})
@NoArgsConstructor(onConstructor = @__(@Autowired))
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Product {
    @Id @NonNull
    private Integer productId;

    @Column @NonNull
    private String title;

    @Column @NonNull
    private String genre;

    @Column @NonNull
    private Float price;

    @Column @NonNull
    private String rating;

    @Column(columnDefinition = "varchar(1000)") @NonNull
    private String endpoint;

    @Column @NonNull
    private String platform;

    @Column(columnDefinition = "varchar(1000)") @NonNull
    private String imageUrl;
}
