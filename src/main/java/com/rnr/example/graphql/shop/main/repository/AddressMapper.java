package com.rnr.example.graphql.shop.main.repository;

import com.rnr.example.graphql.shop.main.model.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.Alias;

@Alias("address")
@Mapper
public interface AddressMapper extends AbstractRepository<Address>{

    @Override
    @Select("SELECT * FROM address WHERE id = #{id}")
    Address findById(int id);
}
