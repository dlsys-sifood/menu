package com.dlsys.sifood.ms.dto;

import com.dlsys.sifood.ms.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponse extends  GenericResponse {
    private Product product;
    private List<Product> products;

    public ProductResponse(String statusCode, String statusResponse, List<String> description, Product product) {
        super(statusCode, statusResponse, description);
        this.product = product;
    }

    public ProductResponse(String statusCode, String statusResponse, List<String> description, List<Product> products) {
        super(statusCode, statusResponse, description);
        this.products = products;
    }
}
