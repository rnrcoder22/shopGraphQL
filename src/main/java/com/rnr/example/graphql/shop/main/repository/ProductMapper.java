package com.rnr.example.graphql.shop.main.repository;

import com.rnr.example.graphql.shop.main.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.sql.Date;

@Mapper
public interface ProductMapper extends AbstractRepository<Product>{
    
    
    @Override
    @Select("SELECT NAME,DESCRIPTION,PRICE FROM product WHERE id = #{id}") 
    Product findById(int id);

    Date getRestockDate(int id);
    
    String availableRegions(int id);
}
