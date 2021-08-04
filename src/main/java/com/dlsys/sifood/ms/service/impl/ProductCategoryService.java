package com.dlsys.sifood.ms.service.impl;

import com.dlsys.sifood.ms.dao.IProductCategoryDao;
import com.dlsys.sifood.ms.entity.ProductCategory;
import com.dlsys.sifood.ms.models.GenericSearch;
import com.dlsys.sifood.ms.response.EntityResponse;
import com.dlsys.sifood.ms.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryService implements IProductCategoryService {

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
            return EntityResponse.getNotFoundMessage();
        }
        return EntityResponse.getSuccessfullListProductCategory(response);
    }

}
