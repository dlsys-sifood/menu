package com.dlsys.sifood.ms.service;

import com.dlsys.sifood.ms.dao.IMenuDao;
import com.dlsys.sifood.ms.dao.IProductDao;
import com.dlsys.sifood.ms.dao.ITypeMenuDao;
import com.dlsys.sifood.ms.dto.GenericResponse;
import com.dlsys.sifood.ms.dto.MenuResponse;
import com.dlsys.sifood.ms.entity.Menu;
import com.dlsys.sifood.ms.models.MenuSearch;
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
public class MenuService implements IMenuService{

    private static final String BADREQUESTCODE = HttpStatus.BAD_REQUEST.toString();
    private static final String BADREQUESTDESCRIPTION = "BAD REQUEST";

    private static final String OKREQUESTCODE = HttpStatus.OK.toString();
    private static final String OKREQUESTDESCRIPTION = "OK";

    @Autowired
    IProductDao productDao;
    @Autowired
    IMenuDao menuDao;
    @Autowired
    ITypeMenuDao typeMenuDao;

    @Override
    public ResponseEntity<?> postMenu(Menu menu, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<Map<String, Object>>(ServiceResponse
                    .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            result.getFieldErrors().stream()
                                    .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                    .collect(Collectors.toList())))
                    , HttpStatus.BAD_REQUEST);
        }

        try {
            menu.setTypeMenu(typeMenuDao.findById(menu.getTypeMenu().getId()).orElse(null));
            menu.setProduct(productDao.findById(menu.getProduct().getId()).orElse(null));
            menuDao.save(menu);
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }

        return new ResponseEntity<Map<String, Object>>(ServiceResponse
                .responseMenu(new MenuResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("exito al guardar"), menu)), HttpStatus.OK);
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
            return new ResponseEntity<Map<String, Object>>(ServiceResponse
                    .responseMenu(new MenuResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                            GenericResponse.toList("consulta no encontrada"), response) )
                    , HttpStatus.OK);
        }

        return new ResponseEntity<>(ServiceResponse
                .responseMenu(new MenuResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("Consulta encontrada"), response)), HttpStatus.OK);
    }
}
