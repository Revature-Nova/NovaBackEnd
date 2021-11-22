package com.revature.nova.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;


/**
 * This is a Model for a product to be displayed in the storefront.
 */

@Entity //More specific than @Component; for hibernate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //Ignore Problem Fields
@Getter //Lombok Getter for all variable fields
@Setter //Lombok Setter for all variable fields
@NoArgsConstructor(onConstructor = @__(@Autowired))
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Table(name = "Product_Table")
public class Product {

    @Id
    // 6 Digit Int? 3 Digit Int? 6 Letter Sequence? AlphaNumeric Sequence?
    private Integer productId;

    @Column
    //The Elder Scrolls V - Skyrim
    private String name;

    @Column
    //RPG
    private String genre;

    @Column
    //19.49
    private Float price;

    @Column
    //M
    private String rating;

    @Column(columnDefinition = "varchar(1000)")
    private String endpoint;

    @Column
    //PC
    private String platform;

    @Column(columnDefinition = "varchar(1000)")
    private String imageUrl;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Cart> cartList;

    /*
    Stretch Goal:
    @Column
    private Integer quantity;

    //Lombok Getter/Setter
    */

}
