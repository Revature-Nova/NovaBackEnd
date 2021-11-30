package com.revature.nova.services;

import com.revature.nova.models.Product;
import com.revature.nova.repositories.ProductRepo;
import com.revature.nova.services.ProductService;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProductServiceTests {
    private ProductService productService;
    private List<Product> testProductList;

    @Mock
    private ProductService mockProductService;

    @Mock
    private ProductRepo productRepo;

    @Autowired
    public ProductServiceTests(ProductService productService) {
        this.productService = productService;
    }

    /**
     * This test checks if display all products returns a list that is not empty when the database is not empty.
     **/
    @Test

    public void Test_successfullyDisplayAllProducts() {
        //Arrange: set up in constructor
        //Act
        testProductList = this.productService.displayAllProducts();
        //Assert
        Assertions.assertTrue(testProductList.size() > 0);
    }

    /**
     * This test checks if display all products returns an empty list if the database is empty.
     **/
    @Test
    public void Test_failToDisplayProductsDueToEmptyDatabase() {
        //Arrange
        //Act
        testProductList = mockProductService.displayAllProducts();
        //Assert
        Assertions.assertFalse(testProductList.size() > 0);
    }


    //	public List<Product> filterProducts(String type, String value) {
//		switch (type) {
//			case "genre":
//				setProductList(repo.findByGenre(value));
//				break;
//			case "platform":
//				setProductList(repo.findByPlatform(value));
//				break;
//			case "rating":
//				setProductList(repo.findByRating(value));
//				break;
//		}
//
//		if(!getSortDirection().equals("None")){
//			sortedProductList(getSortDirection());
//		}
//		return getProductList();
//	}
    @Test
    public void Test_filterProducts() {

    }

    //
//	public List<Product> sortedProductList(String sortingDirection){
//		boolean validSortingDirection = false;
//
//		if (sortingDirection.equals("lowest")) {
//			getProductList().sort(Comparator.comparing(Product::getPrice));
//			validSortingDirection = true;
//		}else if (sortingDirection.equals("highest")) {
//			getProductList().sort((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
//			validSortingDirection = true;
//		}
//		if(validSortingDirection){
//			setSortDirection(sortingDirection);
//		}
//		return getProductList();
//	}
    @Test
    public void Test_sortedProductList() {

    }
//
//	public List<Product> productRange(float rangeMin, float rangeMax){
//		//Gets the list of products with prices between the given range and then updates the productList.
//		setProductList(repo.findByPriceIsBetween(rangeMin,rangeMax));
//
//        /*Checks if a sorting option has been chosen and then sorts the new list so that the user does not have
//        to resort the product list.
//         */
//		if(!getSortDirection().equals("None")){
//			sortedProductList(getSortDirection());
//		}
//		return getProductList();
//	}

    @Test
    public void Test_productRange() {
    }
}
