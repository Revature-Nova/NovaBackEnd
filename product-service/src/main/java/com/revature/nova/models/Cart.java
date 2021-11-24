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

@Entity //More specific than @Component; Tells hibernate to save this model as a table in the database
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //Ignore Problem Fields
@Getter //Lombok Getter for all variable fields; adds boilerplate getters at compile time
@Setter //Lombok Setter for all variable fields; adds boilerplate setters at compile time
@NoArgsConstructor(onConstructor = @__(@Autowired)) //Lombok boilerplate NoArgsConstructor created at compile time
@AllArgsConstructor(onConstructor = @__(@Autowired)) //Lombok boilerplate ArgsConstructor created at compile time
@Table(name = "Cart_Table") //More specific annotation that sets the table name; acts like an alias for the
//// model in the relational database
public class Cart {

    @Id
    private Integer cartId;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(nullable = false, name = "userCart", referencedColumnName = "userCart")
//    private Integer userId;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartList")
    private List<Product> productList;
}
