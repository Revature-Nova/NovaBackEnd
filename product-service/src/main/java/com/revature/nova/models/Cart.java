package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity //More specific than @Component; Tells hibernate to save this model as a table in the database
@Access(value = AccessType.FIELD)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user"}) //Ignore Problem Fields
@Getter //Lombok Getter for all variable fields; adds boilerplate getters at compile time
@Setter //Lombok Setter for all variable fields; adds boilerplate setters at compile time
@NoArgsConstructor(onConstructor = @__(@Autowired)) //Lombok boilerplate NoArgsConstructor created at compile time
@AllArgsConstructor(onConstructor = @__(@Autowired)) //Lombok boilerplate ArgsConstructor created at compile time
public class Cart implements Serializable {
    @PostConstruct
    private void init(){
        cartProducts = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cart")
    private UserModel user;

    @ManyToMany(targetEntity = CartProduct.class)
    private List<CartProduct> cartProducts;

}