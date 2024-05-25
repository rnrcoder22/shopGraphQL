package com.rnr.example.graphql.shop.case2.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class CustomerOrderDto {

    private UUID id;
    private String description;

}
