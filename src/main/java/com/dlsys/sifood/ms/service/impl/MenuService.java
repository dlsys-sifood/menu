package com.dlsys.sifood.ms.service.impl;

import com.dlsys.sifood.ms.dao.IMenuDao;
import com.dlsys.sifood.ms.dao.IProductDao;
import com.dlsys.sifood.ms.dao.ITypeMenuDao;
import com.dlsys.sifood.ms.entity.Menu;
import com.dlsys.sifood.ms.models.MenuSearch;
import com.dlsys.sifood.ms.response.EntityResponse;
import com.dlsys.sifood.ms.service.IMenuService;
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
import java.util.UUID;

@Service
public class MenuService implements IMenuService {

    @Autowired
    private IProductDao productDao;
    @Autowired
    private IMenuDao menuDao;
    @Autowired
    private ITypeMenuDao typeMenuDao;

    @Override
    public ResponseEntity<?> postMenu(Menu menu, BindingResult result) {
        if(result.hasErrors()){
            return EntityResponse.getErrorsFieldResponse(result);
        }
        try {
            menu.setTypeMenu(typeMenuDao.findById(menu.getTypeMenu().getId()).orElse(null));
            menu.setProduct(productDao.findById(menu.getProduct().getId()).orElse(null));
            menuDao.save(menu);
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
        return EntityResponse.getSuccessfullMenu(menu);
    }

    @Override
    public ResponseEntity<?> putMenu(Menu menu, BindingResult result) {
        return null;
    }

    @Override
    public ResponseEntity<?> getMenu(MenuSearch menu) {
        List<Menu> response = new ArrayList<>();
        try {
            response = menuDao.findAll(new Specification<Menu>() {
                @Override
                public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                    Predicate p = cb.conjunction();
                    if (!menu.getName().isEmpty()) {
                        p = cb.and(p, cb.like(root.get("name"), "%" + menu.getName() + "%"));
                    }
                    if (!menu.getId().isEmpty()) {
                        p = cb.and(p, cb.equal(root.get("id"), UUID.fromString(menu.getId())));
                    }
                    if (!menu.getIdTypeMenu().isEmpty()) {
                        p = cb.and(p, cb.equal(root.get("typeMenu"), Integer.parseInt(menu.getIdTypeMenu())));
                    }
                    if (!menu.getIdProduct().isEmpty()) {
                        p = cb.and(p, cb.equal(root.get("product"), UUID.fromString(menu.getIdProduct())));
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
        return EntityResponse.getSuccessfullListMenu(response);
    }
}
