package com.dlsys.sifood.ms.controller;

import com.dlsys.sifood.ms.entity.ProductCategory;
import com.dlsys.sifood.ms.models.GenericSearch;
import com.dlsys.sifood.ms.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/menu")
public class ProductCategoryController {
    @Autowired
    IProductCategoryService productService;

    @RequestMapping(method = RequestMethod.POST, value = "/typeProduct")
    public ResponseEntity<?> postDinningTable(@Valid @RequestBody ProductCategory product, BindingResult result) {
        return productService.postProductCategory(product, result);
    }
    @RequestMapping(value = "/GetInformationTypeProduct", method = RequestMethod.GET)
    public ResponseEntity<?> getproductTable(@RequestBody GenericSearch product) {
        return productService.getProductCategory(product);
    }
    @RequestMapping(value = "/UpdateInformationTypeProduct", method = RequestMethod.PUT)
    public ResponseEntity<?> putproductTable(@Valid @RequestBody ProductCategory product, BindingResult result) {
        return productService.putProductCategory(product, result);
    }
}
