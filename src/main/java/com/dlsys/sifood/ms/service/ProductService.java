package com.dlsys.sifood.ms.service;

import com.dlsys.sifood.ms.dao.IProductCategoryDao;
import com.dlsys.sifood.ms.dao.IProductDao;
import com.dlsys.sifood.ms.dto.GenericResponse;
import com.dlsys.sifood.ms.dto.ProductResponse;
import com.dlsys.sifood.ms.entity.Product;
import com.dlsys.sifood.ms.models.ProductSearch;
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
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService{

    private static final String BADREQUESTCODE = HttpStatus.BAD_REQUEST.toString();
    private static final String BADREQUESTDESCRIPTION = "BAD REQUEST";

    private static final String OKREQUESTCODE = HttpStatus.OK.toString();
    private static final String OKREQUESTDESCRIPTION = "OK";

    @Autowired
    IProductDao productDao;
    @Autowired
    IProductCategoryDao categoryDao;

    @Override
    public ResponseEntity<?> postProduct(Product product, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<Map<String, Object>>(ServiceResponse
                    .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            result.getFieldErrors().stream()
                                    .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                    .collect(Collectors.toList())))
                    , HttpStatus.BAD_REQUEST);
        }

        try {
            product.setCategory(categoryDao.findById(product.getCategory().getId()).orElse(null));
            productDao.save(product);
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }

        return new ResponseEntity<Map<String, Object>>(ServiceResponse
                .responseProduct(new ProductResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                        GenericResponse.toList("exito al guardar"), product)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> putProduct(Product product, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<Map<String, Object>>(ServiceResponse
                    .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            result.getFieldErrors().stream()
                                    .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                    .collect(Collectors.toList())))
                    , HttpStatus.BAD_REQUEST);
        }

        try {
            product.setCategory(categoryDao.findById(product.getCategory().getId()).orElse(null));
            productDao.save(product);
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }

        return new ResponseEntity<Map<String, Object>>(ServiceResponse
                .responseProduct(new ProductResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                        GenericResponse.toList("exito al guardar"), product)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getProduct(ProductSearch product) {
        List<Product> response = new ArrayList<>();

        try {
            response = productDao.findAll(new Specification<Product>() {
                @Override
                public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                    Predicate p = cb.conjunction();
                    if (!product.getName().isEmpty()) {
                        p = cb.and(p, cb.like(root.get("name"), "%" + product.getName() + "%"));
                    }
                    if (!product.getId().isEmpty()) {
                        p = cb.and(p, cb.equal(root.get("id"), UUID.fromString(product.getId())));
                    }
                    return p;
                }
            });
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }

        if(response.isEmpty()){
            return new ResponseEntity<Map<String, Object>>(ServiceResponse
                    .responseProduct(new ProductResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            GenericResponse.toList("consulta no encontrada"), response) )
                    , HttpStatus.OK);
        }

        return new ResponseEntity<>(ServiceResponse
                .responseProduct(new ProductResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("Consulta encontrada"), response)), HttpStatus.OK);
    }
}
