package com.example.catalogservice.vo;

import lombok.Data;

@Data
public class ResponseCatalog {
    private Long id;
    private String productId;
    private String productName;
    private Integer stock;
    private Integer unitPrice;
}
