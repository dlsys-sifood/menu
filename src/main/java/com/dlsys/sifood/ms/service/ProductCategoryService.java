package com.dlsys.sifood.ms.service;

import com.dlsys.sifood.ms.dao.IProductCategoryDao;
import com.dlsys.sifood.ms.dto.GenericResponse;
import com.dlsys.sifood.ms.dto.ProductCategoryResponse;
import com.dlsys.sifood.ms.entity.ProductCategory;
import com.dlsys.sifood.ms.models.GenericSearch;
import com.dlsys.sifood.ms.response.EntityResponse;
import com.dlsys.sifood.ms.response.ListResponse;
import com.dlsys.sifood.ms.service.impl.IProductCategoryService;
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

@Service
public class ProductCategoryService implements IProductCategoryService {

    private static final String BADREQUESTCODE = HttpStatus.BAD_REQUEST.toString();
    private static final String BADREQUESTDESCRIPTION = "BAD REQUEST";

    @Autowired
    private IProductCategoryDao productDao;

    @Override
    public ResponseEntity<?> postProductCategory(ProductCategory product, BindingResult result) {
        if(result.hasErrors()){
            return EntityResponse.getErrorsFieldResponse(result);
        }
        try {
            productDao.save(product);
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
        return EntityResponse.getSuccessfullProductCategory(product);
    }

    @Override
    public ResponseEntity<?> putProductCategory(ProductCategory product, BindingResult result) {
        if(result.hasErrors()){
            return EntityResponse.getErrorsFieldResponse(result);
        }
        try {
            productDao.save(product);
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
        return EntityResponse.getSuccessfullProductCategory(product);
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
            return new ResponseEntity<Map<String, Object>>(ListResponse
                    .responseProductCategory(new ProductCategoryResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            GenericResponse.toList("consulta no encontrada"), response) )
                    , HttpStatus.OK);
        }
        return EntityResponse.getSuccessfullListProductCategory(response);
    }

}
