package com.dlsys.sifood.ms.dto;

import com.dlsys.sifood.ms.entity.ProductCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCategoryResponse extends GenericResponse{

    private ProductCategory product;
    private List<ProductCategory> products;

    public ProductCategoryResponse(String statusCode, String statusResponse, List<String> description, ProductCategory product) {
        super(statusCode, statusResponse, description);
        this.product = product;
    }

    public ProductCategoryResponse(String statusCode, String statusResponse, List<String> description, List<ProductCategory> products) {
        super(statusCode, statusResponse, description);
        this.products = products;
    }
}
