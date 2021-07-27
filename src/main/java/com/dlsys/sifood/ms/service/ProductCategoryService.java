package com.dlsys.sifood.ms.service;

import com.dlsys.sifood.ms.dao.IProductCategoryDao;
import com.dlsys.sifood.ms.dao.ITypeMenuDao;
import com.dlsys.sifood.ms.dto.GenericResponse;
import com.dlsys.sifood.ms.dto.ProductCategoryResponse;
import com.dlsys.sifood.ms.dto.TypeMenuResponse;
import com.dlsys.sifood.ms.entity.ProductCategory;
import com.dlsys.sifood.ms.entity.TypeMenu;
import com.dlsys.sifood.ms.models.GenericSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService implements IProductCategoryService{

    private static final String BADREQUESTCODE = HttpStatus.BAD_REQUEST.toString();
    private static final String BADREQUESTDESCRIPTION = "BAD REQUEST";

    private static final String OKREQUESTCODE = HttpStatus.OK.toString();
    private static final String OKREQUESTDESCRIPTION = "OK";

    @Autowired
    IProductCategoryDao productDao;

    @Override
    public ResponseEntity<?> postProductCategory(ProductCategory product, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<Map<String, Object>>(ServiceResponse
                    .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            result.getFieldErrors().stream()
                                    .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                    .collect(Collectors.toList())))
                    , HttpStatus.BAD_REQUEST);
        }

        try {
            productDao.save(product);
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }

        return new ResponseEntity<Map<String, Object>>(ServiceResponse
                .responseProductCategory(new ProductCategoryResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                        GenericResponse.toList("exito al guardar"), product)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> putProductCategory(ProductCategory product, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<Map<String, Object>>(ServiceResponse
                    .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            result.getFieldErrors().stream()
                                    .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                    .collect(Collectors.toList())))
                    , HttpStatus.BAD_REQUEST);
        }

        try {
            productDao.save(product);
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }

        return new ResponseEntity<Map<String, Object>>(ServiceResponse
                .responseProductCategory(new ProductCategoryResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("exito al guardar"), product)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getProductCategory(GenericSearch product) {
        List<ProductCategory> response = new ArrayList<>();

        try {
            response = productDao.findAll(new Specification<ProductCategory>() {
                @Override
                public Predicate toPredicate(Root<ProductCategory> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                    Predicate p = cb.conjunction();
                    if (!product.getName().isEmpty()) {
                        p = cb.and(p, cb.like(root.get("name"), "%" + product.getName() + "%"));
                    }
                    if (!product.getId().isEmpty()) {
                        p = cb.and(p, cb.equal(root.get("id"), Integer.parseInt(product.getId())));
                    }
                    return p;
                }
            });
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }

        if(response.isEmpty()){
            return new ResponseEntity<Map<String, Object>>(ServiceResponse
                    .responseProductCategory(new ProductCategoryResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            GenericResponse.toList("consulta no encontrada"), response) )
                    , HttpStatus.OK);
        }

        return new ResponseEntity<>(ServiceResponse
                .responseProductCategory(new ProductCategoryResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("Consulta encontrada"), response)), HttpStatus.OK);
    }

}
