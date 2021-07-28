package com.dlsys.sifood.ms.service.impl;

import com.dlsys.sifood.ms.entity.ProductCategory;
import com.dlsys.sifood.ms.models.GenericSearch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface IProductCategoryService{
    public ResponseEntity<?> postProductCategory(ProductCategory product, BindingResult result);
    public ResponseEntity<?> putProductCategory(ProductCategory product, BindingResult result);
    public ResponseEntity<?> getProductCategory(GenericSearch product);
}
