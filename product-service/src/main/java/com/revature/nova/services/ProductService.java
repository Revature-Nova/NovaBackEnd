package com.revature.nova.services;

import com.revature.nova.models.Product;
import com.revature.nova.repositories.ProductRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
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
    private String sortDirection = "None"; //Used to maintain the sorting direction.

    @Autowired
    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    /**
     * This method searches for a list of products containing the search parameter
     * @param search with search term
     * @return list of products
     */
    public List<Product> getProductsContainingTitle(String search)
    {
        //Finds product(s) by their title and sets the productList equal to the results
        setProductList(repo.findByTitleContainingIgnoreCase(search));

        //Maintains sorting direction
        if(!getSortDirection().equals("None")){
            sortedProductList(getSortDirection());
        }
        return getProductList();
    }

    /**
     * This method gets all products. Can also be used to reset the list if the user wants to remove the selected
     * filter. Also resets the sorting direction.
     *
     * @return Returns a list containing all unsorted products.
     */
    public List<Product> displayAllProducts(){
        setProductList(repo.findAll());
        setSortDirection("None");
        return getProductList();
    }

    /**
     * This method returns a filtered list of products using the given filter type and filter value.
     * The filter type and filter value need to match what is in the database exactly.
     * This method abstracts away resorting the list so that the user does not have to choose a sorting method
     * everytime they change the filter.
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

        /*Checks if a sorting option has been chosen and then sorts the new list so that the user does not have
        to resort the product list.
         */
        if(!getSortDirection().equals("None")){
             sortedProductList(getSortDirection());
        }
        return getProductList();
    }

    /**
     *This method sorts the product list.
     *
     * @param sortingDirection This method requires the direction in which the list needs to be sorted.
     *                         If the sortingDirection = "lowest", then a list sorted by the lowest price to
     *                         the highest price is returned.
     *                         If the sortingDirection = "highest", then a list sorted by the highest to the
     *                         lowest price is returned.
     * @return
     */
    public List<Product> sortedProductList(String sortingDirection){
        boolean validSortingDirection = false;
        /*See https://www.geeksforgeeks.org/how-to-sort-an-arraylist-of-objects-by-property-in-java/ for example on
    How to Sort an ArrayList of Objects by Property in Java
     */
        if (sortingDirection.equals("lowest")) {
            getProductList().sort(Comparator.comparing(Product::getPrice));
            validSortingDirection = true;
        }else if (sortingDirection.equals("highest")) {
            getProductList().sort((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
            validSortingDirection = true;
        }

        /*If a valid sorting direction was inputted, then this if statement assigns the sortingDirection to
        the sortDirection variable in this class.
         */
        if(validSortingDirection){
            setSortDirection(sortingDirection);
        }
        return getProductList();
    }
  
    /**
     * This method gets a list of products with prices between a given range.
     *
     * @param rangeMin This variable sets the lower/min end of the desired price range.
     * @param rangeMax This variable sets the upper/max end of the desired price range.
     * @return This method returns a list of products with prices that fall in the desired range.
     */
    public List<Product> productRange(float rangeMin, float rangeMax){
        //Gets the list of products with prices between the given range and then updates the productList.
        setProductList(repo.findByPriceIsBetween(rangeMin,rangeMax));

        /*Checks if a sorting option has been chosen and then sorts the new list so that the user does not have
        to resort the product list.
         */
        if(!getSortDirection().equals("None")){
            sortedProductList(getSortDirection());
        }
        return getProductList();
    }

    /**
     * Search our database for a product by ID
     * @param id for object
     * @return product with that id
     */
    public Product getProductById(Integer id)
    {
        return repo.getById(id);
    }
}
