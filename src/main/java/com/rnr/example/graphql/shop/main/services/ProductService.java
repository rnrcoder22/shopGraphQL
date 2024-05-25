package com.rnr.example.graphql.shop.main.services;

import com.rnr.example.graphql.shop.main.model.Product;
import com.rnr.example.graphql.shop.main.repository.ProductMapper;
import org.springframework.stereotype.Service;
import java.sql.Date;

@Service
public class ProductService {
    
    private final ProductMapper productMapper;

    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }
    
    public Product findById(int id) {
        return productMapper.findById(id);
    }
    
    public Date getRestockForProduct(int id) {
        return productMapper.getRestockDate(id);
    }
    
    public String getAvailableRegionsForProduct(int id) {
        return productMapper.availableRegions(id);
    }
}
