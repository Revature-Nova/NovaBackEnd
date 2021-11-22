package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Cart_Table")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor(onConstructor = @__(@Autowired))
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Cart {

    @Id
    private Integer cartId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "userCart", referencedColumnName = "userCart")
    private Integer userId;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    @JoinColumn(name = "cart", referencedColumnName = "cart", nullable = false)
    private List<Product> productList;
}
