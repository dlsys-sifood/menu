package com.dlsys.sifood.ms.service.impl;

import com.dlsys.sifood.ms.entity.Product;
import com.dlsys.sifood.ms.models.ProductSearch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface IProductService {
    public ResponseEntity<?> postProduct(Product product, BindingResult result);
    public ResponseEntity<?> putProduct(Product product, BindingResult result);
    public ResponseEntity<?> getProduct(ProductSearch product);
}
