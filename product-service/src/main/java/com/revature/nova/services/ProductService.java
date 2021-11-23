package com.revature.nova.services;

import com.revature.nova.models.Product;
import com.revature.nova.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This service bean is used to talk to its designated repository and handle data retrieval for 'Product'
 *
 * @author Chris Oh, Michael Reece
 * @date 11/22/21
 */
@Service
@Transactional
public class ProductService {

    private final ProductRepo repo;

    @Autowired
    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    public List<Product> getProductsContainingTitle(String search)
    {
        return repo.findByNameContaining(search);
    }
}
