package com.example.catalogservice.dto;

import lombok.Data;

@Data
public class CatalogDto {

    private Long id;
    private String productId;
    private String productName;
    private Integer stock;
    private Integer unitPrice;
}
