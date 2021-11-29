package com.revature.nova.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartProductId;


    @ManyToMany(targetEntity = Cart.class)
    private List<Cart> cart;

    @OneToOne
    private Product product;

    @Column
    private int quantity;
}
