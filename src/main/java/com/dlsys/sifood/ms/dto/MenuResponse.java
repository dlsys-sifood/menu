package com.dlsys.sifood.ms.dto;

import com.dlsys.sifood.ms.entity.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuResponse extends GenericResponse {

    private Menu menu;
    private List<Menu> menus;

    public MenuResponse(String statusCode, String statusResponse, List<String> description, Menu menu) {
        super(statusCode, statusResponse, description);
        this.menu = menu;
    }

    public MenuResponse(String statusCode, String statusResponse, List<String> description, List<Menu> menus) {
        super(statusCode, statusResponse, description);
        this.menus = menus;
    }

}
