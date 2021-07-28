package com.dlsys.sifood.ms.response;

import com.dlsys.sifood.ms.dto.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ListResponse {

    public static Map<String, Object> responseGeneric(GenericResponse generic){
        Map<String, Object> response = new HashMap<>();
        response.put("response", generic);
        return response;
    }

    public static Map<String, Object> responseTypeMenu(TypeMenuResponse menu){
        Map<String, Object> response = new HashMap<>();
        response.put("response", menu);
        return response;
    }

    public static Map<String, Object> responseProductCategory(ProductCategoryResponse product){
        Map<String, Object> response = new HashMap<>();
        response.put("response", product);
        return response;
    }

    public static Map<String, Object> responseProduct(ProductResponse product){
        Map<String, Object> response = new HashMap<>();
        response.put("response", product);
        return response;
    }

    public static Map<String, Object> responseMenu(MenuResponse menu){
        Map<String, Object> response = new HashMap<>();
        response.put("response", menu);
        return response;
    }
}
