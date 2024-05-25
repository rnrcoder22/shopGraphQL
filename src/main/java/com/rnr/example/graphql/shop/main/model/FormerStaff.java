package com.rnr.example.graphql.shop.main.model;

import java.sql.Date;

public class FormerStaff extends Staff {
    
    private Date terminationDate;

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }
}
