package com.revature.nova.services;

import com.revature.nova.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductService {
    private final ProductRepo repo;

    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    /**
     * This method gets the products from the database
     */
    public void getProduct(){
        repo.findAll();
    }
}
