package com.revature.nova.services;

import com.revature.nova.models.Product;
import com.revature.nova.repositories.ProductRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class ProductServiceTests {
    //This is the service that we are testing
    private ProductService productService;
    //This is the actual product list that is returned by the method being tested
    private List<Product> actualProductList = new ArrayList<>();
    //This is the list of products that each method should be returning
    private List<Product> expectedProductList = new ArrayList<>();
    /*This list simulates the database and contains all necessary examples for testing
    the methods in the ProductService Class.
     */
    private List<Product> mockDatabaseData = new ArrayList<>();
    //This is the string that determines which filter will be used
    private String type;
    //This is the string that determines the value that the products will be filtered by.
    private String value;
    //This is the sorting direction for the product list
    private String sortingDirection;

    /*
        This is the mock repo. If you don't define what this mock repo should return when specific methods are
        called, then an empty list will be returned since no data is retrieved. This mock repo does not interact
        with the real database.
         */
    @Mock
    private ProductRepo mockProductRepo;

    @BeforeEach
    void setUp() {
        productService = new ProductService(mockProductRepo);

        //Setting up mock database
        Product product = new Product(119,"The Legend of Zelda: Breath of the Wild", "RPG", 59.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-breath-of-the-wild?key=87ad23cdc737468884eb0216a7ba8df9:", "Nintendo Switch", "https://imgur.com/onC0oCn");
        Product product1 = new Product(120, "Subnautica", "Adventure", 29.99f, "E10+", "https://rawg.io/api/games/subnautica?key=87ad23cdc737468884eb0216a7ba8df9", "PlayStation 4", "https://imgur.com/JkX9r1e");
        Product product2 = new Product(121, "The Legend of Zelda: Skyward Sword", "Adventure", 19.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Wii", "https://imgur.com/VvU45oV");
        Product product3 = new Product(122,"Thief", "Stealth", 21.27f, "Mature", "https://rawg.io/api/games/thief?key=87ad23cdc737468884eb0216a7ba8df9", "PlayStation 3", "https://imgur.com/Z0EPE84");
        Product product4 = new Product(123, "The Legend of Zelda: Skyward Sword", "Adventure", 39.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Nintendo Switch", "https://imgur.com/VvU45oV");
        mockDatabaseData.add(product);
        mockDatabaseData.add(product1);
        mockDatabaseData.add(product2);
        mockDatabaseData.add(product3);
        mockDatabaseData.add(product4);
    }

    //Reset everything that is reused in each test to prevent any side effects
    @AfterEach
    void tearDown() {
        //Print out actualProductList so that you can make sure you are getting the correct results.
        if(actualProductList.isEmpty()){
            System.out.println(actualProductList);
        }
        for (Product p: actualProductList) {
            System.out.println("Products: " + p.getProductId() + ", " + p.getTitle() + ", " + p.getGenre()
                    + ", " + p.getPlatform() + ", " + p.getRating() + ", $" + p.getPrice());
        }
        //Reset all lists and the productService
        type = null;
        value = null;
        sortingDirection = null;
        actualProductList.clear();
        expectedProductList.clear();
        mockDatabaseData.clear();
        productService = null;
    }


    ////////////////////////     UNIT TESTS     ////////////////////////

    //Tests for the displayAllProducts method in the ProductService class.
    /**
     * This test checks if displayAllProducts returns a list that contains all products in the database.
     **/
    @Test
    public void Test_successfullyDisplayAllProducts() {
        //Arrange
        Mockito.doReturn(mockDatabaseData).when(mockProductRepo).findAll();
        //Act
        actualProductList = this.productService.displayAllProducts();
        //Assert
        Assertions.assertEquals(mockDatabaseData, actualProductList);
        System.out.println("\nTest for successfully displaying all products:");
    }

    /**
     * This test checks if display all products returns an empty list if the database is empty.
     **/
    @Test
    public void Test_failToDisplayProductsDueToEmptyDatabase() {
        //Arrange
        mockDatabaseData.clear();
        mockDatabaseData = Collections.emptyList();
        Mockito.doReturn(mockDatabaseData).when(mockProductRepo).findAll();
        //Act
        actualProductList = productService.displayAllProducts();
        //Assert
        Assertions.assertTrue(actualProductList.isEmpty());
        System.out.println("\nTest for failure to display products due to an empty database:");
    }

    //Tests for the displayAllProducts method above.


    //Tests for the filterProducts method.

    //Tests for success:

    /**
     * Tests that genre case returns a list of products with the given genre and that the method successfully routes
     * to the correct case.
     */
    @Test
    public void Test_filterProductsGenreCaseSuccess() {
        //Arrange
        type = "genre";
        value = "Adventure";
        Product product = new Product(121, "The Legend of Zelda: Skyward Sword", "Adventure", 19.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Wii", "https://imgur.com/VvU45oV");
        Product product1 = new Product(123, "The Legend of Zelda: Skyward Sword", "Adventure", 39.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Nintendo Switch", "https://imgur.com/VvU45oV");
        expectedProductList.add(product);
        expectedProductList.add(product1);
        Mockito.doReturn(expectedProductList).when(mockProductRepo).findByGenre(value);
        //Act
        actualProductList = this.productService.filterProducts(type,value);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
        System.out.println("\nTest for successfully filtering by genre:");
    }

    /**
     * Tests that platform case returns a list of products with the given platform and that the method successfully routes
     *      * to the correct case.
     */
    @Test
    public void Test_filterProductsPlatformCaseSuccess() {
        //Arrange
        type = "platform";
        value = "Nintendo Switch";
        Product product = new Product(119,"The Legend of Zelda: Breath of the Wild", "RPG", 59.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-breath-of-the-wild?key=87ad23cdc737468884eb0216a7ba8df9:", "Nintendo Switch", "https://imgur.com/onC0oCn");
        Product product1 = new Product(123, "The Legend of Zelda: Skyward Sword", "Adventure", 39.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Nintendo Switch", "https://imgur.com/VvU45oV");
        expectedProductList.add(product);
        expectedProductList.add(product1);
        Mockito.doReturn(expectedProductList).when(mockProductRepo).findByPlatform(value);
        //Act
        actualProductList = this.productService.filterProducts(type,value);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
        System.out.println("\nTest for successfully filtering by platform:");
    }

    /**
     * Tests that rating case returns a list of products with the given rating and that the method successfully routes
     *      * to the correct case.
     */
    @Test
    public void Test_filterProductsRatingCaseSuccess() {
        //Arrange
        type = "rating";
        value = "E10+";
        Product product = new Product(119,"The Legend of Zelda: Breath of the Wild", "RPG", 59.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-breath-of-the-wild?key=87ad23cdc737468884eb0216a7ba8df9:", "Nintendo Switch", "https://imgur.com/onC0oCn");
        Product product1 = new Product(120, "Subnautica", "Adventure", 29.99f, "E10+", "https://rawg.io/api/games/subnautica?key=87ad23cdc737468884eb0216a7ba8df9", "PlayStation 4", "https://imgur.com/JkX9r1e");
        Product product2 = new Product(121, "The Legend of Zelda: Skyward Sword", "Adventure", 19.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Wii", "https://imgur.com/VvU45oV");
        Product product3 = new Product(123, "The Legend of Zelda: Skyward Sword", "Adventure", 39.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Nintendo Switch", "https://imgur.com/VvU45oV");
        expectedProductList.add(product);
        expectedProductList.add(product1);
        expectedProductList.add(product2);
        expectedProductList.add(product3);
        Mockito.doReturn(expectedProductList).when(mockProductRepo).findByRating(value);
        //Act
        actualProductList = this.productService.filterProducts(type,value);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
        System.out.println("\nTest for successfully filtering by rating:");
    }

    /**
     * Tests that the method maintains sorting direction when it goes through the unfiltered mock database.
     * Ascending order.
     */
    @Test
    public void Test_filterProductsMaintainSortingDirectionSuccessAsc() {
        //Arrange
        sortingDirection = "lowest";
        expectedProductList = mockDatabaseData;
        expectedProductList.sort(Comparator.comparing(Product::getPrice));
        Mockito.doReturn(mockDatabaseData).when(mockProductRepo).findAll();
        productService.setSortDirection(sortingDirection);
        //Act
        actualProductList = this.productService.displayAllProducts();
        //Assert
        Assertions.assertEquals(expectedProductList, actualProductList);
        System.out.println("\nIntegrated test for successfully displaying all products while maintaining ascending soring order:");
    }

    /**
     * Tests that the method maintains sorting direction when it goes through the unfiltered mock database.
     * Descending order
     */
    @Test
    public void Test_filterProductsMaintainSortingDirectionSuccessDesc() {
        //Arrange
        sortingDirection = "highest";
        expectedProductList = mockDatabaseData;
        expectedProductList.sort((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
        Mockito.doReturn(mockDatabaseData).when(mockProductRepo).findAll();
        productService.setSortDirection(sortingDirection);
        //Act
        actualProductList = this.productService.displayAllProducts();
        //Assert
        Assertions.assertEquals(expectedProductList, actualProductList);
        System.out.println("\nTest for successfully displaying all products while maintaining descending soring order:");
    }

    /**
     * Tests that the method maintains sorting direction when it goes through the genre.
     * Ascending order.
     */
    @Test
    public void Test_filterProductsByGenreAndMaintainSortingDirectionSuccessAsc() {
        //Arrange
        mockDatabaseData.clear();
        sortingDirection = "lowest";
        type = "genre";
        value = "Adventure";
        Product product = new Product(121, "The Legend of Zelda: Skyward Sword", "Adventure", 19.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Wii", "https://imgur.com/VvU45oV");
        Product product1 = new Product(123, "The Legend of Zelda: Skyward Sword", "Adventure", 39.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Nintendo Switch", "https://imgur.com/VvU45oV");
        mockDatabaseData.add(product);
        mockDatabaseData.add(product1);
        expectedProductList = mockDatabaseData;
        expectedProductList.sort(Comparator.comparing(Product::getPrice));
        Mockito.doReturn(mockDatabaseData).when(mockProductRepo).findByGenre(value);
        productService.setSortDirection(sortingDirection);
        //Act
        actualProductList = this.productService.filterProducts(type,value);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
        System.out.println("\nTest for successfully filtering by genre while maintaining ascending sorting order:");
    }

    /**
     * Tests that the method maintains sorting direction when it goes through the genre.
     * Descending order.
     */
    @Test
    public void Test_filterProductsByGenreAndMaintainSortingDirectionSuccessDesc() {
        //Arrange
        mockDatabaseData.clear();
        sortingDirection = "highest";
        type = "genre";
        value = "Adventure";
        Product product = new Product(121, "The Legend of Zelda: Skyward Sword", "Adventure", 19.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Wii", "https://imgur.com/VvU45oV");
        Product product1 = new Product(123, "The Legend of Zelda: Skyward Sword", "Adventure", 39.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Nintendo Switch", "https://imgur.com/VvU45oV");
        mockDatabaseData.add(product);
        mockDatabaseData.add(product1);
        expectedProductList = mockDatabaseData;
        expectedProductList.sort((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
        Mockito.doReturn(mockDatabaseData).when(mockProductRepo).findByGenre(value);
        productService.setSortDirection(sortingDirection);
        //Act
        actualProductList = this.productService.filterProducts(type,value);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
        System.out.println("\nTest for successfully filtering by genre while maintaining descending sorting order:");
    }

    /**
     * Tests that the method maintains sorting direction when it goes through the platform case
     * Ascending order
     */
    @Test
    public void Test_filterProductsByPlatformAndMaintainSortingDirectionSuccessAsc() {
        //Arrange
        mockDatabaseData.clear();
        sortingDirection = "lowest";
        type = "platform";
        value = "Nintendo Switch";
        Product product = new Product(119,"The Legend of Zelda: Breath of the Wild", "RPG", 59.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-breath-of-the-wild?key=87ad23cdc737468884eb0216a7ba8df9:", "Nintendo Switch", "https://imgur.com/onC0oCn");
        Product product1 = new Product(123, "The Legend of Zelda: Skyward Sword", "Adventure", 39.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Nintendo Switch", "https://imgur.com/VvU45oV");
        mockDatabaseData.add(product);
        mockDatabaseData.add(product1);
        expectedProductList = mockDatabaseData;
        expectedProductList.sort(Comparator.comparing(Product::getPrice));
        Mockito.doReturn(mockDatabaseData).when(mockProductRepo).findByPlatform(value);
        productService.setSortDirection(sortingDirection);
        //Act
        actualProductList = this.productService.filterProducts(type,value);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
        System.out.println("\nTest for successfully filtering by platform while maintaining ascending sorting order:");
    }

    /**
     * Tests that the method maintains sorting direction when it goes through the platform case
     * Descending order
     */
    @Test
    public void Test_filterProductsByPlatformAndMaintainSortingDirectionSuccessDesc() {
        //Arrange
        mockDatabaseData.clear();
        sortingDirection = "highest";
        type = "platform";
        value = "Nintendo Switch";
        Product product = new Product(119,"The Legend of Zelda: Breath of the Wild", "RPG", 59.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-breath-of-the-wild?key=87ad23cdc737468884eb0216a7ba8df9:", "Nintendo Switch", "https://imgur.com/onC0oCn");
        Product product1 = new Product(123, "The Legend of Zelda: Skyward Sword", "Adventure", 39.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Nintendo Switch", "https://imgur.com/VvU45oV");
        mockDatabaseData.add(product);
        mockDatabaseData.add(product1);
        expectedProductList = mockDatabaseData;
        expectedProductList.sort((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
        Mockito.doReturn(expectedProductList).when(mockProductRepo).findByPlatform(value);
        productService.setSortDirection(sortingDirection);
        //Act
        actualProductList = this.productService.filterProducts(type,value);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
        System.out.println("\nTest for successfully filtering by platform while maintaining descending sorting order:");
    }

    /**
     * Tests that the method maintains sorting direction when it goes through the rating case
     * Ascending order
     */
    @Test
    public void Test_filterProductsByRatingAndMaintainSortingDirectionSuccessAsc() {
        //Arrange
        mockDatabaseData.clear();
        sortingDirection = "lowest";
        type = "rating";
        value = "E10+";
        Product product = new Product(119,"The Legend of Zelda: Breath of the Wild", "RPG", 59.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-breath-of-the-wild?key=87ad23cdc737468884eb0216a7ba8df9:", "Nintendo Switch", "https://imgur.com/onC0oCn");
        Product product1 = new Product(120, "Subnautica", "Adventure", 29.99f, "E10+", "https://rawg.io/api/games/subnautica?key=87ad23cdc737468884eb0216a7ba8df9", "PlayStation 4", "https://imgur.com/JkX9r1e");
        Product product2 = new Product(121, "The Legend of Zelda: Skyward Sword", "Adventure", 19.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Wii", "https://imgur.com/VvU45oV");
        Product product3 = new Product(123, "The Legend of Zelda: Skyward Sword", "Adventure", 39.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Nintendo Switch", "https://imgur.com/VvU45oV");
        mockDatabaseData.add(product);
        mockDatabaseData.add(product1);
        mockDatabaseData.add(product2);
        mockDatabaseData.add(product3);
        expectedProductList = mockDatabaseData;
        expectedProductList.sort(Comparator.comparing(Product::getPrice));
        Mockito.doReturn(mockDatabaseData).when(mockProductRepo).findByRating(value);
        productService.setSortDirection(sortingDirection);
        //Act
        actualProductList = this.productService.filterProducts(type,value);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
        System.out.println("\nTest for successfully filtering by rating while maintaining ascending sorting order:");
    }

    /**
     * Tests that the method maintains sorting direction when it goes through the rating case
     * Descending order
     */
    @Test
    public void Test_filterProductsByRatingAndMaintainSortingDirectionSuccessDesc() {
        //Arrange
        mockDatabaseData.clear();
        sortingDirection = "highest";
        type = "rating";
        value = "E10+";
        Product product = new Product(119,"The Legend of Zelda: Breath of the Wild", "RPG", 59.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-breath-of-the-wild?key=87ad23cdc737468884eb0216a7ba8df9:", "Nintendo Switch", "https://imgur.com/onC0oCn");
        Product product1 = new Product(120, "Subnautica", "Adventure", 29.99f, "E10+", "https://rawg.io/api/games/subnautica?key=87ad23cdc737468884eb0216a7ba8df9", "PlayStation 4", "https://imgur.com/JkX9r1e");
        Product product2 = new Product(121, "The Legend of Zelda: Skyward Sword", "Adventure", 19.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Wii", "https://imgur.com/VvU45oV");
        Product product3 = new Product(123, "The Legend of Zelda: Skyward Sword", "Adventure", 39.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Nintendo Switch", "https://imgur.com/VvU45oV");
        mockDatabaseData.add(product);
        mockDatabaseData.add(product1);
        mockDatabaseData.add(product2);
        mockDatabaseData.add(product3);
        expectedProductList = mockDatabaseData;
        expectedProductList.sort((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
        Mockito.doReturn(mockDatabaseData).when(mockProductRepo).findByRating(value);
        productService.setSortDirection(sortingDirection);
        //Act
        actualProductList = this.productService.filterProducts(type,value);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
        System.out.println("\nTest for successfully filtering by rating while maintaining descending sorting order:");
    }

    //Tests for failure:

    /**
     * Tests that the genre case returns an empty list if there is no matching value in the mock database
     * Able to get into the genre switch case, but returns an empty list there are not any genres that match
     * the given value. Test that it does correctly return that empty list.
     */
    @Test
    public  void Test_filterProductsGenreCaseFailure(){
        //Arrange
        type = "genre";
        value = "Drama";
        expectedProductList = Collections.emptyList();
        Mockito.doReturn(expectedProductList).when(mockProductRepo).findByGenre(value);
        //Act
        actualProductList = this.productService.filterProducts(type,value);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
        System.out.println("\nTest for failing to filter by genre:");
    }

    /**
     *Tests that platform case returns an empty list if there is no matching value in the mock database
     * Able to get into the platform switch case, but returns an empty list there are not any platforms that match
     * the given value. Test that it does correctly return that empty list.
     */
    @Test
    public  void Test_filterProductsPlatformCaseFailure(){
        //Arrange
        type = "platform";
        value = "Book";
        expectedProductList = Collections.emptyList();
        Mockito.doReturn(expectedProductList).when(mockProductRepo).findByPlatform(value);
        //Act
        actualProductList = this.productService.filterProducts(type,value);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
        System.out.println("\nTest for failing to filter by platform:");
    }

    /**
     *Tests that rating case returns an empty list if there is no matching value in the mock database
     * Able to get into the rating switch case, but returns an empty list there are not any platforms that match
     * the given value. Test that it does correctly return that empty list.
     */
    @Test
    public  void Test_filterProductsRatingCaseFailure(){
        //Arrange
        type = "rating";
        value = "Bad";
        expectedProductList = Collections.emptyList();
        Mockito.doReturn(expectedProductList).when(mockProductRepo).findByRating(value);
        //Act
        actualProductList = this.productService.filterProducts(type,value);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
        System.out.println("\nTest for failing to filter by rating:");
    }

    /**
     * Tests that the method returns an empty list if none of the cases are selected
     */
    @Test
    public  void Test_filterProductsFailureDueToInvalidCaseType(){
        //Arrange
        type = "year";
        value = "2030";
        expectedProductList = Collections.emptyList();
        //Act
        actualProductList = this.productService.filterProducts(type,value);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
        System.out.println("\nTest for failing to filter due to invalid case type:");
    }

    /**
     * This tests that the sorting direction is not maintained if an invalid sorting direction is given.
     */
    @Test
    public void Test_filterProductsMaintainSortingDirectionFailureDueToInvalidSortingDirection() {
        //Arrange
        sortingDirection = "upper";
        expectedProductList = mockDatabaseData;
        Mockito.doReturn(mockDatabaseData).when(mockProductRepo).findAll();
        productService.setSortDirection(sortingDirection);
        //Act
        actualProductList = this.productService.displayAllProducts();
        //Assert
        Assertions.assertEquals(expectedProductList, actualProductList);
        System.out.println("\nIntegrated test for successfully displaying all products while maintaining ascending soring order:");
    }

    //Tests for the filterProducts method above

    //Tests for the sortedProductList method

    //Tests for success:

    /**
     * Tests that the list is sorted from the lowest price to the highest price when the sortingDirection = "lowest"
    */
    @Test
    public void Test_sortedProductListLowestToHighestSuccess() {
        //Arrange
        sortingDirection = "lowest";
        expectedProductList = mockDatabaseData;
        expectedProductList.sort(Comparator.comparing(Product::getPrice));
        productService.setProductList(mockDatabaseData);
        //Act
        actualProductList = productService.sortedProductList(sortingDirection);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
    }

    /**
     * Tests that the list is sorted from the highest price to the lowest price when the sortingDirection = "highest"
     */
    @Test
    public void Test_sortedProductListHighestToLowestSuccess() {
        //Arrange
        sortingDirection = "highest";
        expectedProductList = mockDatabaseData;
        expectedProductList.sort((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
        productService.setProductList(mockDatabaseData);
        //Act
        actualProductList = productService.sortedProductList(sortingDirection);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
    }

    /**
     * Tests that the sortDirection variable is set equal to the sortingDirection
     */
    @Test
    public void Test_sortedProductListSetSortDirectionSuccess() {
        //Arrange
        String actualSortDirection;
        sortingDirection = "lowest";
        String expectedSortDirection = sortingDirection;
        productService.setProductList(mockDatabaseData);
        //Act
        productService.sortedProductList(sortingDirection);
        actualSortDirection = productService.getSortDirection();
        System.out.println("Actual sorting direction: " + actualSortDirection);
        //Assert
        Assertions.assertEquals(expectedSortDirection,actualSortDirection);
    }

    //Tests for failure:

    /**
     *Tests that an unsorted list is returned if the sorting direction is not a valid value
     */
    @Test
    public void Test_sortedProductListSortingListFailure() {
        //Arrange
        sortingDirection = "starting from the middle";
        expectedProductList = mockDatabaseData;
        productService.setProductList(mockDatabaseData);
        //Act
        actualProductList = productService.sortedProductList(sortingDirection);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
    }

    /**
     *Tests that the sortDirection variable is not set equal to the sortingDirection if the sortingDirection
     * is invalid
     */
    @Test
    public void Test_sortedProductListSetSortDirectionFailure() {
        //Arrange
        String actualSortDirection;
        sortingDirection = "upper";
        //This is what the sortingDirection will be if productService.getSortDirection() runs successfully and
        //sorts the list.
        String expectedSortDirection = sortingDirection;
        productService.setProductList(mockDatabaseData);
        //Act
        actualProductList = productService.sortedProductList(sortingDirection);
        actualSortDirection = productService.getSortDirection();
        System.out.println("Actual sorting direction: " + actualSortDirection);
        //Assert
        Assertions.assertNotEquals(expectedSortDirection,actualSortDirection);
        Assertions.assertEquals(mockDatabaseData,actualProductList);
    }

    /**
     * Tests that an empty list is returned if the product list is empty. This would occur when there has not been
     * any calls to the getProductsContainingTitle, displayAllProducts, or filterProducts methods, thus the
     * product list has not been set.
     */
    @Test
    public void Test_sortedProductReturnsAnEmptyListIfTheProductListIsEmpty(){
        //Arrange
        sortingDirection = "lowest";
        expectedProductList = Collections.emptyList();
        productService.setProductList(expectedProductList);
        //Act
        actualProductList = productService.sortedProductList(sortingDirection);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
    }

    //Tests for the sortedProductList method above

    //Tests for the productRange method

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

    //Tests for success:

    /**
     * Tests that productRange successfully returns a list of products within the given price range
     */
    @Test
    public void Test_productRangeSuccess() {
        //Arrange
        float min = 10f;
        float max = 30f;
        Product product = new Product(120, "Subnautica", "Adventure", 29.99f, "E10+", "https://rawg.io/api/games/subnautica?key=87ad23cdc737468884eb0216a7ba8df9", "PlayStation 4", "https://imgur.com/JkX9r1e");
        Product product1 = new Product(121, "The Legend of Zelda: Skyward Sword", "Adventure", 19.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Wii", "https://imgur.com/VvU45oV");
        Product product2= new Product(122,"Thief", "Stealth", 21.27f, "Mature", "https://rawg.io/api/games/thief?key=87ad23cdc737468884eb0216a7ba8df9", "PlayStation 3", "https://imgur.com/Z0EPE84");
        expectedProductList.add(product);
        expectedProductList.add(product1);
        expectedProductList.add(product2);
        Mockito.doReturn(expectedProductList).when(mockProductRepo).findByPriceIsBetween(min,max);
        //Act
        actualProductList = productService.productRange(min,max);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
    }

    //Tests for failure:

    /**
     * Tests that an empty list is returned if an invalid price range is provided
     */
    @Test
    public void Test_productRangeReturnsAnEmptyList() {
        //Arrange
        float min = 60f;
        float max = 70f;
        expectedProductList = Collections.emptyList();
        Mockito.doReturn(expectedProductList).when(mockProductRepo).findByPriceIsBetween(min,max);
        //Act
        actualProductList = productService.productRange(min,max);
        //Assert
        Assertions.assertEquals(expectedProductList,actualProductList);
    }

    //Tests for the productRange method above

    //Tests for the getProductsContainingTitle method

    //unit testing: want to know it's returning a list of products, look at individual products within the list
//    public List<Product> getProductsContainingTitle(String search)
//    {
//        //Finds product(s) by their title and sets the productList equal to the results
//        setProductList(repo.findByTitleContaining(search));
//
//        //Maintains sorting direction
//        if(!getSortDirection().equals("None")){
//            sortedProductList(getSortDirection());
//        }
//        return getProductList();
//    }

    //Tests for the getProductsContainingTitle method above

    //Tests for getting a product by its id

//    public Product getProductById(Integer id)
//    {
//        return repo.getById(id);
//    }

    //Tests for getting a product by its id above
}
