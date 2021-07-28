package com.dlsys.sifood.ms.controller;

import com.dlsys.sifood.ms.entity.Menu;
import com.dlsys.sifood.ms.models.MenuSearch;
import com.dlsys.sifood.ms.service.impl.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<?> postDinningTable(@Valid @RequestBody Menu menu, BindingResult result) {
        return menuService.postMenu(menu, result);
    }
    @RequestMapping(value = "/getMenuInformation", method = RequestMethod.GET)
    public ResponseEntity<?> getproductTable(@RequestBody MenuSearch menu) {
        return menuService.getMenu(menu);
    }
    @RequestMapping(value = "/updateMenuInformation", method = RequestMethod.PUT)
    public ResponseEntity<?> putproductTable(@Valid @RequestBody Menu menu, BindingResult result) {
        return menuService.putMenu(menu, result);
    }

}
