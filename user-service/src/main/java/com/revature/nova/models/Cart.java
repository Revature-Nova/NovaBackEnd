package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * POJO used to store information about a product
 *
 * @date 11/30/2021
 * @author Kollier Martin
 */
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor(onConstructor = @__(@Autowired))
public class Cart {
    private Integer cartId;
    private List<Product> productList;
    private String dateTime;
}
