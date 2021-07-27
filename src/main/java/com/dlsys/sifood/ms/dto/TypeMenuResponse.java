package com.dlsys.sifood.ms.dto;

import com.dlsys.sifood.ms.entity.TypeMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class TypeMenuResponse extends GenericResponse{
    private TypeMenu menu;
    private List<TypeMenu> menus;

    public TypeMenuResponse(String statusCode, String statusResponse, List<String> description, TypeMenu menu) {
        super(statusCode, statusResponse, description);
        this.menu = menu;
    }

    public TypeMenuResponse(String statusCode, String statusResponse, List<String> description, List<TypeMenu> menus) {
        super(statusCode, statusResponse, description);
        this.menus = menus;
    }
}
