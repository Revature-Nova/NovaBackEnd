package com.revature.nova.controllers;

import com.revature.nova.models.Product;
import com.revature.nova.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
  
    private final ProductRepo repo;

    @Autowired
    public TestController(ProductRepo repo) {
        this.repo = repo;
    }

    @CrossOrigin
    @GetMapping(value="/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAll(){
        return repo.findAll();
    }

    @GetMapping(value="/test/genre", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getByGenre(@RequestParam String genre){
        return repo.findByGenre(genre);
    }

    @GetMapping(value="/test/rating", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getByRating(@RequestParam String rating){
        return repo.findByRating(rating);
    }

    @GetMapping(value="/test/platform", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getByPlatform(@RequestParam String platform){
        return repo.findByPlatform(platform);
    }

    @GetMapping(value="/test/range", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getBy(@RequestParam float min, float max){
        return repo.findByPriceIsBetween(min,max);
    }
}
