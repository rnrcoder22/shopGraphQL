package com.rnr.example.graphql.shop.main.model.input;

import com.rnr.example.graphql.shop.main.model.Person;
import java.math.BigDecimal;
import java.sql.Date;

public class StaffInput extends Person {
    
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
