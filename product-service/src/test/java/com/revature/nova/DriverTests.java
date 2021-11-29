package com.revature.nova;

import com.revature.nova.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Comparator;
import java.util.List;

@SpringBootTest
class DriverTests {

	@Test
	void contextLoads() {
	}

//	public List<Product> displayAllProducts(){
//		setProductList(repo.findAll());
//		setSortDirection("None");
//		return getProductList();
//	}
//


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

}
