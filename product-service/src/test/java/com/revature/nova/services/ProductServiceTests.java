package com.revature.nova.services;

import com.revature.nova.models.Product;
import com.revature.nova.repositories.ProductRepo;
import com.revature.nova.services.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProductServiceTests {
    private ProductService productService;
    private List<Product> testProductList;
    private List<Product> mockDatabaseData;

    @Mock
    private ProductRepo mockProductRepo;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.initMocks(this); depreciated, but might need it
        productService = new ProductService(mockProductRepo);
        Product product = new Product(119,"The Legend of Zelda: Breath of the Wild", "RPG", 59.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-breath-of-the-wild?key=87ad23cdc737468884eb0216a7ba8df9:", "Nintendo Switch", "https://imgur.com/onC0oCn");
        Product product1 = new Product(120, "Subnautica", "Adventure", 29.99f, "E10+", "https://rawg.io/api/games/subnautica?key=87ad23cdc737468884eb0216a7ba8df9", "PlayStation 4", "https://imgur.com/JkX9r1e");
        Product product2 = new Product(121, "The Legend of Zelda: Skyward Sword", "Adventure", 19.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Wii", "https://imgur.com/VvU45oV");
        Product product3 = new Product(122,"Thief", "Stealth", 21.27f, "Mature", "https://rawg.io/api/games/thief?key=87ad23cdc737468884eb0216a7ba8df9", "PlayStation 3", "https://imgur.com/Z0EPE84");
        List<Product> temp = new ArrayList<>();
        temp.add(product);
        temp.add(product1);
        temp.add(product2);
        temp.add(product3);
        setMockDatabaseData(temp);
    }

    @AfterEach
    void tearDown() {
        productService = null;
    }

    /**
     * This test checks if display all products returns a list that is not empty when the database is not empty.
     **/
    @Test
    public void Test_successfullyDisplayAllProducts() {
        //Arrange
        doReturn(getMockDatabaseData()).when(mockProductRepo).findAll();
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
        setMockDatabaseData(Collections.emptyList());
        doReturn(getMockDatabaseData()).when(mockProductRepo).findAll();
        //Act
        testProductList = productService.displayAllProducts();
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

    public List<Product> getMockDatabaseData() {
        return mockDatabaseData;
    }

    public void setMockDatabaseData(List<Product> mockDatabaseData) {
        this.mockDatabaseData = mockDatabaseData;
    }
}
