package com.revature.nova.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct {

    @Id
    @ManyToMany
    private Cart cart;

    @OneToOne
    private Product product;

    @Column
    private int quantity;
}
