package com.rnr.example.graphql.shop.main.controllers;

import com.rnr.example.graphql.shop.main.model.Product;
import com.rnr.example.graphql.shop.main.services.ProductService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.sql.Date;

@Controller
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @QueryMapping("prodId")
    public Product findById(int id) {
        return productService.findById(id);
    }
    
    @QueryMapping("restockDate")
    public Date getRestookDate(int prodId){
        return productService.getRestockForProduct(prodId);
    }
    
    @QueryMapping("availability")
    public String getAvailableRegions(int prodId){
        return productService.getAvailableRegionsForProduct(prodId);
    }
}
