package com.rnr.example.graphql.shop.main.repository;

import com.rnr.example.graphql.shop.main.model.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import java.util.List;

@Mapper
public interface ClientMapper extends AbstractRepository<Client>{
     
     @Override
     @QueryMapping("clientById")
     Client findById(int id);
     
     @Select("SELECT * FROM clients") 
     List<Client> findAll(int id);
}
