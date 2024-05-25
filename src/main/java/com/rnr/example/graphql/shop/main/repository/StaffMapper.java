package com.rnr.example.graphql.shop.main.repository;

import com.rnr.example.graphql.shop.main.model.Staff;
import com.rnr.example.graphql.shop.main.model.input.SalaryRange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import java.util.List;

@Mapper
public interface StaffMapper extends AbstractRepository<Staff>{

    /* 
    *   Note that we are relying on a query that is stored in StaffMapper.xml 
    *   This is because we need to retrieve other data related to a staff member.
    *   When it comes to join data from multiple tables, it is worth storing queries in XML
    */ 
    @Override
    Staff findById(@Param("id") int id);
    
    @Results(id = "staffResultMap", value = {
      @Result(property = "firstName", column = "FIRST_NAME"),
      @Result(property = "lastName", column = "LAST_NAME")
    })

    List<Staff> findCurrentStaff();
    
    List<Staff> findFormerStaff();

    List<Staff> findAll();
    
    int insertStaff(Staff staff);

    List<Staff> findStaffWithinSalary(SalaryRange salaryRange);
}
