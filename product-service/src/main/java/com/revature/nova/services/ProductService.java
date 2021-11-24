package com.revature.nova.services;

import com.revature.nova.models.Product;
import com.revature.nova.repositories.ProductRepo;
import javassist.NotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * This service bean is used to talk to its designated repository and handle data retrieval for 'Product'
 *
 * @author Chris Oh, Michael Reece, Brittany Lowell
 * @date 11/22/21
 */
@Service
@Transactional
@Getter
@Setter
public class ProductService {

    private final ProductRepo repo;
    private List<Product> productList;

    @Autowired
    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }


    /**
     * This method gets all products.
     *
     * @return Returns a list containing all products
     */
    public List<Product> displayAllProducts(){
        return repo.findAll();
    }

    /**
     * This method returns a filtered list of products using the given filter type and filter value.
     * The filter type and filter value need to match what is in the database exactly.
     *
     * @param type This inputs determines how the products will be filtered. The valid filter types are:
     *             genre, platform, and rating.
     * @param value This input determines the value the products will be filtered by. Valid filter values include:
     *              For genre: RPG, Action, Horror, etc.
     *              For platform: PlayStation 4, Switch, etc.
     *              For rating: Mature, E10+, Teen, etc.
     * @return Returns a list of filtered products.
     */
    public List<Product> filterProducts(String type, String value) {
        switch (type) {
            case "genre":
                setProductList(repo.findByGenre(value));
                break;
            case "platform":
                setProductList(repo.findByPlatform(value));
                break;
            case "rating":
                setProductList(repo.findByRating(value));
                break;
        }
        return productList;
    }

    public List<Product> sortedList(){
        List<Product> list = getProductList();
        return null;
    }

    public List<Product> getProductsContainingTitle(String search)
    {
        return repo.findByTitleContaining(search);
    }
}
