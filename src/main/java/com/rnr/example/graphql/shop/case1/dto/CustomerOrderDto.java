package com.rnr.example.graphql.shop.case1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class CustomerOrderDto {

    private UUID id;
    private String description;

}
