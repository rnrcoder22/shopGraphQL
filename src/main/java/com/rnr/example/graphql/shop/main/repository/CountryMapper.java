package com.rnr.example.graphql.shop.main.repository;

import com.rnr.example.graphql.shop.main.model.Country;
import com.rnr.example.graphql.shop.main.model.input.CountryInput;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

/*
    Since we are relying on simple queries to deal with countries we use
    mybatis annotations instead xml mapping configurations.
*/
@Mapper
public interface CountryMapper extends AbstractRepository<Country>{
    
    @Override
    @Select("SELECT * FROM countries WHERE id = #{id}") 
    Country findById(int id);
    
    @Select("SELECT * FROM countries") 
    List<Country> findAll();
    
    @Delete("DELETE FROM countries WHERE id = #{id}")
	int deleteById(Country country);

	@Insert("INSERT INTO countries(country) VALUES (#{country})")
	int insert(CountryInput country);
	
	@Insert("INSERT INTO countries(country) VALUES (#{countryName})")
	int insertWithName(String countryName);

	@Update("Update countries set country=#{country} where id=#{id}")
	int update(CountryInput country);
}
