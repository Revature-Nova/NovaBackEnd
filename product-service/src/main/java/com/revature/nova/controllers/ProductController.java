package com.revature.nova.controllers;


import com.revature.nova.models.Product;
import com.revature.nova.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

/**
 * ProductController
 * @author Michael Reece, Brittany Lowell
 * @date 11/22/21
 * Handles requests that deal with manipulating product data
 */

@RestController
@RequestMapping(value = "/NovaGames")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Search our database for something matching title
     * @param search term path variable
     * @return list of products that match
     */
    @GetMapping(value = "/title/{search}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Product> searchByTitle(@PathVariable String search)
    {
        //the search is case sensitive, I am going to, by default, convert the first letter to uppercase to help the search

        //query our database for products containing search param string
        System.out.println(search);
        return productService.getProductsContainingTitle(search);
    }
}
