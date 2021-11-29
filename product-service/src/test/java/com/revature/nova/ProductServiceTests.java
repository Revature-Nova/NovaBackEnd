package com.revature.nova;

import com.revature.nova.models.Product;
import com.revature.nova.repositories.ProductRepo;
import com.revature.nova.services.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class ProductServiceTests {
	private ProductService productService;
	private List<Product> testProductList;
	private final ProductRepo productRepo;

	public ProductServiceTests(ProductService productService, ProductRepo productRepo) {
		this.productService = productService;
		this.productRepo = productRepo;
	}

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	public void tearDown(){
		this.productService = null;
	}

	//	public List<Product> displayAllProducts(){
//		setProductList(repo.findAll());
//		setSortDirection("None");
//		return getProductList();
//	}
//	/*
//	Unit testing:
//	Success: instantiates a list of products, fills the list, and then returns it
//	Failure: returns an empty list
//	 */


	@Test
	public void Test_successfullyDisplayAllProducts(){
		//Arrange: Completed in the constructor
		//Act
		testProductList = this.productService.displayAllProducts();
		//Assert
		Assertions.assertNotEquals(0, testProductList.size());
	}

	@Test
	public void Test_failToDisplayProductsDueToEmptyDatabase(){
		//Arrange
		productRepo.deleteAll();
		//Act
		testProductList = productService.displayAllProducts();
		//Assert
		Assertions.assertEquals(0, testProductList.size());
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
	public void Test_filterProducts(){

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
	public void Test_sortedProductList(){

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
	public void Test_productRange(){

	}

}
