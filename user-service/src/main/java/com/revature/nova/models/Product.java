package com.revature.nova.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;


/**
 * This is a Model for a product to be displayed in the storefront.
 */

@Entity //More specific than @Component; Tells hibernate to save this model as a table in the database
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //Ignore Problem Fields
@Getter //Lombok Getter for all variable fields; adds boilerplate getters at compile time
@Setter //Lombok Setter for all variable fields; adds boilerplate setters at compile time
@NoArgsConstructor(onConstructor = @__(@Autowired)) //Lombok boilerplate NoArgsConstructor created at compile time
@AllArgsConstructor(onConstructor = @__(@Autowired)) //Lombok boilerplate ArgsConstructor created at compile time
@Table(name = "Product_Table") //More specific annotation that sets the table name; acts like an alias for the
// model in the relational database
/**
 * This class defines the product model used throughout the project.
 */
public class Product {

    @Id
    //Ex/Re/Ie:  6 Digit Int? 3 Digit Int? 6 Letter Sequence? AlphaNumeric Sequence?
    private Integer productId;

    @Column
    //Ex/Re/Ie: The Elder Scrolls V - Skyrim
    private String title;

    @Column
    //Ex/Re/Ie: RPG
    private String genre;

    @Column
    //Ex/Re/Ie: 19.49
    private Float price;

    @Column
    //Ex/Re/Ie: M
    private String rating;

    @Column(columnDefinition = "varchar(1000)")
    private String endpoint;

    @Column
    //Ex/Re/Ie: PC
    private String platform;

    @Column(columnDefinition = "varchar(1000)")
    private String imageUrl;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Cart> cartList;

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


    /*
    Stretch Goal:
    @Column
    private Integer quantity;

    */

}
