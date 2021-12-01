package com.revature.nova.services;

import com.revature.nova.models.Product;
import com.revature.nova.repositories.ProductRepo;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Getter
@Setter
class ProductServiceTests {
    private ProductService productService;
    private List<Product> testProductList;
    private List<Product> mockDatabaseData;
    private List<Product> expectedList;

    @Mock
    private ProductRepo mockProductRepo;

    @BeforeEach
    void setUp() {
        productService = new ProductService(mockProductRepo);
        Product product = new Product(119,"The Legend of Zelda: Breath of the Wild", "RPG", 59.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-breath-of-the-wild?key=87ad23cdc737468884eb0216a7ba8df9:", "Nintendo Switch", "https://imgur.com/onC0oCn");
        Product product1 = new Product(120, "Subnautica", "Adventure", 29.99f, "E10+", "https://rawg.io/api/games/subnautica?key=87ad23cdc737468884eb0216a7ba8df9", "PlayStation 4", "https://imgur.com/JkX9r1e");
        Product product2 = new Product(121, "The Legend of Zelda: Skyward Sword", "Adventure", 19.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Wii", "https://imgur.com/VvU45oV");
        Product product3 = new Product(122,"Thief", "Stealth", 21.27f, "Mature", "https://rawg.io/api/games/thief?key=87ad23cdc737468884eb0216a7ba8df9", "PlayStation 3", "https://imgur.com/Z0EPE84");
        Product product4 = new Product(123, "The Legend of Zelda: Skyward Sword", "Adventure", 39.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Nintendo Switch", "https://imgur.com/VvU45oV");
        List<Product> temp = new ArrayList<>();
        temp.add(product);
        temp.add(product1);
        temp.add(product2);
        temp.add(product3);
        temp.add(product4);
        setMockDatabaseData(temp);
    }

    @AfterEach
    void tearDown() {
        setExpectedList(null);
        setMockDatabaseData(null);
        productService = null;
    }

    //Tests for the displayAllProducts method in the ProductService class.
    /**
     * This test checks if display all products returns a list that is not empty when the database is not empty.
     **/
    @Test
    public void Test_successfullyDisplayAllProducts() {
        //Arrange
        Mockito.doReturn(getMockDatabaseData()).when(mockProductRepo).findAll();
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
        Mockito.doReturn(getMockDatabaseData()).when(mockProductRepo).findAll();
        //Act
        testProductList = productService.displayAllProducts();
        //Assert
        Assertions.assertFalse(testProductList.size() > 0);
    }


    //Tests for the displayAllProducts method above.


    //Tests for the filterProducts method.


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

    /*
    Tests for success:
    - Tests that genre case returns a list of products with the given genre
    - Tests that platform case returns a list of products with the given platform
    - Tests that rating case returns a list of products with the given rating
    - Tests that the method maintains sorting direction
     */
    /**
     * Tests that genre case returns a list of products with the given genre
     */
    @Test
    public void Test_filterProductsGenreCaseSuccess() {
        //Arrange
        //Act
        //Assert
    }

    /**
     * Tests that platform case returns a list of products with the given platform
     */
    @Test
    public void Test_filterProductsPlatformCaseSuccess() {
        //Arrange
        //Act
        //Assert
    }

    /**
     * Tests that rating case returns a list of products with the given rating
     */
    @Test
    public void Test_filterProductsRatingCaseSuccess() {
        //Arrange
        //Act
        //Assert
    }

    /**
     * Tests that the method maintains sorting direction
     */
    @Test
    public void Test_filterProductsMaintainSortingDirectionSuccess() {
        //Arrange
        //Act
        //Assert
    }

    /*
    Tests for failure:
    - Tests that the genre case returns an empty list if there is no matching value in the mock database
    - Tests that platform case returns an empty list if there is no matching value in the mock database
    - Tests that rating case returns an empty list if there is no matching value in the mock database
    - Tests that the method returns the mockDatabase list if none of the cases are selected
    - Tests that the sorting direction is reset if the sorting direction = "None"
     */

    /**
     * Tests that the genre case returns an empty list if there is no matching value in the mock database
     */
    @Test
    public  void Test_filterProductsGenreCaseFailure(){
        //Arrange
        //Act
        //Assert
    }

    /**
     *Tests that platform case returns an empty list if there is no matching value in the mock database
     */
    @Test
    public  void Test_filterProductsPlatformCaseFailure(){
        //Arrange
        //Act
        //Assert
    }

    /**
     *Tests that rating case returns an empty list if there is no matching value in the mock database
     */
    @Test
    public  void Test_filterProductsRatingCaseFailure(){
        //Arrange
        //Act
        //Assert
    }

    /**
     * Tests that the method returns the mockDatabase list if none of the cases are selected
     */
    @Test
    public  void Test_filterProductsFailureDueToInvalidCaseType(){
        //Arrange
        //Act
        //Assert
    }

    /**
     * Tests that the sorting direction is reset if the sorting direction = "None"
     */
    @Test
    public  void Test_filterProductsMaintainSortingDirectionFailure(){
        //Arrange
        //Act
        //Assert
    }

    //Tests for the filterProducts method above

    //Tests for the sortedProductList method

    /*
    Tests for success:
    - Tests that the list is sorted from the lowest price to the highest price when the sortingDirection = "lowest"
    - Tests that the list is sorted from the lowest price to the highest price when the sortingDirection = "highest"
    - Tests that the sortDirection variable is set equal to the sortingDirection
    Tests for failure:
    - Tests that an unsorted list is returned if the sorting direction is not a valid value
    - Tests that the sortDirection variable is not set equal to the sortingDirection if the sortingDirection is invalid
     */

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
