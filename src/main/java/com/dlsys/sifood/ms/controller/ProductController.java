package com.dlsys.sifood.ms.controller;

import com.dlsys.sifood.ms.entity.Product;
import com.dlsys.sifood.ms.models.ProductSearch;
import com.dlsys.sifood.ms.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@EnableSwagger2
@RestController
@RequestMapping(value = "/menu")
public class ProductController {
    @Autowired
    private IProductService productService;

    @RequestMapping(method = RequestMethod.POST, value = "/product")
    public ResponseEntity<?> postDinningTable(@Valid @RequestBody Product product, BindingResult result) {
        return productService.postProduct(product, result);
    }
    @RequestMapping(value = "/GetInformationProduct", method = RequestMethod.GET)
    public ResponseEntity<?> getproductTable(@RequestBody ProductSearch product) {
        return productService.getProduct(product);
    }
    @RequestMapping(value = "/UpdateInformationProduct", method = RequestMethod.PUT)
    public ResponseEntity<?> putproductTable(@Valid @RequestBody Product product, BindingResult result) {
        return productService.putProduct(product, result);
    }
}
