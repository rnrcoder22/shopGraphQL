package com.rnr.example.graphql.shop.main.model.input;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "create")
public class SalaryRange {
    
    public BigDecimal minimum;
    public BigDecimal maximum;
    
}
