package com.dlsys.sifood.ms.service.impl;

import com.dlsys.sifood.ms.dao.ITypeMenuDao;
import com.dlsys.sifood.ms.entity.TypeMenu;
import com.dlsys.sifood.ms.models.GenericSearch;
import com.dlsys.sifood.ms.response.EntityResponse;
import com.dlsys.sifood.ms.service.ITypeMenuService;
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
public class TypeMenuService implements ITypeMenuService {

    @Autowired
    private ITypeMenuDao typeMenuDao;

    @Override
    public ResponseEntity<?> postMenuType(TypeMenu menu, BindingResult result) {
        if(result.hasErrors()){
            return EntityResponse.getErrorsFieldResponse(result);
        }
        try {
            typeMenuDao.save(menu);
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
        return EntityResponse.getSuccessfullTypeMenu(menu);
    }

    @Override
    public ResponseEntity<?> putMenuType(TypeMenu menu, BindingResult result) {
        if(result.hasErrors()){
            return EntityResponse.getErrorsFieldResponse(result);
        }
        try {
            typeMenuDao.save(menu);
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
        return EntityResponse.getSuccessfullTypeMenu(menu);
    }

    @Override
    public ResponseEntity<?> getMenuType(GenericSearch menu) {
        List<TypeMenu> dinning = new ArrayList<>();
        try {
            dinning = typeMenuDao.findAll(new Specification<TypeMenu>() {
                @Override
                public Predicate toPredicate(Root<TypeMenu> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                    Predicate p = cb.conjunction();
                    if (!menu.getName().isEmpty()) {
                        p = cb.and(p, cb.like(root.get("name"), "%" + menu.getName() + "%"));
                    }
                    if (!menu.getId().isEmpty()) {
                        p = cb.and(p, cb.equal(root.get("id"), Integer.parseInt(menu.getId())));
                    }
                    return p;
                }
            });
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
        if(dinning.isEmpty()){
            return EntityResponse.getNotFoundMessage();
        }
        return EntityResponse.getSuccessfullListTypeMenu(dinning);
    }
}
