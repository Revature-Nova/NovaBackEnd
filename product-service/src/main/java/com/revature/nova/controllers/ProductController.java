package com.revature.nova.controllers;


import com.revature.nova.models.Product;
import com.revature.nova.rawg.DescriptionInfo;
import com.revature.nova.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProductController
 * @author Michael Reece, Brittany Lowell
 * @date 11/22/21
 * Handles requests that deal with manipulating product data
 */

@RestController
@RequestMapping(value = "/Nova")
public class ProductController {
    // Add CartClient here
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
        //query our database for products containing search param string
        return productService.getProductsContainingTitle(search);
    }

    /**
     * This method handles the get request for filtering the movie list.
     *
     * @param type This inputs determines how the products will be filtered. The valid filter types are:
     *             genre, platform, and rating.
     * @param value This input determines the value the products will be filtered by. Valid filter values include:
     *              For genre: RPG, Action, Horror, etc.
     *              For platform: PlayStation 4, Switch, etc.
     *              For rating: Mature, E10+, Teen, etc.
     * @return This method returns the filtered list.
     */
    

    @GetMapping(value = "/filter/{type}/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Product> getFilteredList(@PathVariable String type, @PathVariable String value){
        return productService.filterProducts(type, value);
    }

    /**
     *This method handles the get request for sorting the movie list.
     *
     * @param sortingDirection This input determines the direction in which the products will be filtered.
     *                         If the sortingDirection = "lowest", then a list sorted by the lowest price to
     *                         the highest price is returned.
     *                         If the sortingDirection = "highest", then a list sorted by the highest to the
     *                         lowest price is returned.
     * @return This method returns the sorted list.
     *
     */
    @GetMapping(value = "/sort/{sortingDirection}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Product> getSortedList(@PathVariable String sortingDirection){
        return productService.sortedProductList(sortingDirection);
    }

    /**
     * This method handles the get request for obtaining a list of products with prices between the given range.
     * @param min This variable sets the lower/min end of the desired price range.
     * @param max This variable sets the upper/max end of the desired price range.
     * @return This method returns a list of products with prices that fall in the desired range.
     */
    @GetMapping(value = "/range/{min}/{max}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Product> getRange(@PathVariable float min, @PathVariable float max){
        return productService.productRange(min,max);
    }

    /**
     * This method handles the get request for obtaining a list of all products.
     *
     * @return Returns a list containing all products.
     */
    

    @GetMapping(value = "/display", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Product> displayAll(){
        return productService.displayAllProducts();
    }

    /**
     * Search our database for something matching title
     * @param id for object
     * @return description for the specified object
     */
    
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public String gameDescription(@PathVariable Integer id)
    {
        //get product from database
        Product currentProduct = productService.getProductById(id);

        return DescriptionInfo.getDescription(currentProduct);
    }
}
