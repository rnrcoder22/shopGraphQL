package com.rnr.example.graphql.shop.main.model;

import org.apache.ibatis.type.Alias;
import java.math.BigDecimal;
import java.sql.Date;

@Alias("staff")
public class Staff extends Person {
    
    private Date hireDate;
    
    
    private BigDecimal salary;

    private boolean disabled;

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

 

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    
        public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    
}
