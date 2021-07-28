package com.dlsys.sifood.ms.service;

import com.dlsys.sifood.ms.dto.*;
import com.dlsys.sifood.ms.entity.Menu;
import com.dlsys.sifood.ms.entity.Product;
import com.dlsys.sifood.ms.entity.ProductCategory;
import com.dlsys.sifood.ms.entity.TypeMenu;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GenericService {

    private static final String BADREQUESTCODE = HttpStatus.BAD_REQUEST.toString();
    private static final String BADREQUESTDESCRIPTION = "BAD REQUEST";

    private static final String OKREQUESTCODE = HttpStatus.OK.toString();
    private static final String OKREQUESTDESCRIPTION = "OK";

    public static ResponseEntity<?> getErrorsFieldResponse(BindingResult result){
        return new ResponseEntity<Map<String, Object>>(ServiceResponse
                .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                        result.getFieldErrors().stream()
                                .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                .collect(Collectors.toList())))
                , HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> getSuccessfullListMenu(List<Menu> response){
        return new ResponseEntity<>(ServiceResponse
                .responseMenu(new MenuResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("Consulta encontrada"), response)), HttpStatus.OK);
    }

    public static ResponseEntity<?> getSuccessfullMenu(Menu response){
        return new ResponseEntity<>(ServiceResponse
                .responseMenu(new MenuResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("Exito al guardar"), response)), HttpStatus.OK);
    }

    public static ResponseEntity<?> getSuccessfullListProduct(List<Product> response){
        return new ResponseEntity<Map<String, Object>>(ServiceResponse
                .responseProduct(new ProductResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("consulta encontrada"), response)), HttpStatus.OK);
    }

    public static ResponseEntity<?> getSuccessfullProduct(Product response){
        return new ResponseEntity<Map<String, Object>>(ServiceResponse
                .responseProduct(new ProductResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("exito al guardar"), response)), HttpStatus.OK);
    }

    public static ResponseEntity<?> getSuccessfullListProductCategory(List<ProductCategory> response){
        return new ResponseEntity<>(ServiceResponse
                .responseProductCategory(new ProductCategoryResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("Consulta encontrada"), response)), HttpStatus.OK);
    }

    public static ResponseEntity<?> getSuccessfullProductCategory(ProductCategory response){
        return new ResponseEntity<Map<String, Object>>(ServiceResponse
                .responseProductCategory(new ProductCategoryResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("exito al guardar"), response)), HttpStatus.OK);
    }

    public static ResponseEntity<?> getSuccessfullListTypeMenu(List<TypeMenu> response){
        return new ResponseEntity<>(ServiceResponse
                .responseTypeMenu(new TypeMenuResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("Consulta encontrada"), response)), HttpStatus.OK);
    }

    public static ResponseEntity<?> getSuccessfullTypeMenu(TypeMenu response){
        return new ResponseEntity<Map<String, Object>>(ServiceResponse
                .responseTypeMenu(new TypeMenuResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("exito al guardar"), response)), HttpStatus.OK);
    }
}
