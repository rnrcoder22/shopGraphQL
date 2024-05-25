package com.rnr.example.graphql.shop.main.model;

import java.sql.Date;

public class Client extends Person {
    
    Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
