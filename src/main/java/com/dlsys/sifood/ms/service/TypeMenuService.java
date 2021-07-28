package com.dlsys.sifood.ms.service;

import com.dlsys.sifood.ms.dao.ITypeMenuDao;
import com.dlsys.sifood.ms.dto.GenericResponse;
import com.dlsys.sifood.ms.dto.TypeMenuResponse;
import com.dlsys.sifood.ms.entity.TypeMenu;
import com.dlsys.sifood.ms.models.GenericSearch;
import com.dlsys.sifood.ms.response.EntityResponse;
import com.dlsys.sifood.ms.response.ListResponse;
import com.dlsys.sifood.ms.service.impl.ITypeMenuService;
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
public class TypeMenuService implements ITypeMenuService {

    private static final String BADREQUESTCODE = HttpStatus.BAD_REQUEST.toString();
    private static final String BADREQUESTDESCRIPTION = "BAD REQUEST";

    private static final String OKREQUESTCODE = HttpStatus.OK.toString();
    private static final String OKREQUESTDESCRIPTION = "OK";

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
            return new ResponseEntity<Map<String, Object>>(ListResponse
                    .responseTypeMenu(new TypeMenuResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            GenericResponse.toList("consulta no encontrada"), dinning))
                    , HttpStatus.OK);
        }
        return EntityResponse.getSuccessfullListTypeMenu(dinning);
    }
}
