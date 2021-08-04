package com.dlsys.sifood.ms.controller;

import com.dlsys.sifood.ms.entity.TypeMenu;
import com.dlsys.sifood.ms.models.GenericSearch;
import com.dlsys.sifood.ms.service.ITypeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@EnableSwagger2
@RestController
@RequestMapping(value = "/menu")
public class TypeMenuController {

    @Autowired
    private ITypeMenuService typeService;

    @RequestMapping(method = RequestMethod.POST, value = "/typeMenu")
    public ResponseEntity<?> postDinningTable(@Valid @RequestBody TypeMenu dinning, BindingResult result) {
        return typeService.postMenuType(dinning, result);
    }
    @RequestMapping(value = "/GetInformationTypeMenu", method = RequestMethod.GET)
    public ResponseEntity<?> getDinningTable(@RequestBody GenericSearch dinning) {
        return typeService.getMenuType(dinning);
    }
    @RequestMapping(value = "/UpdateInformationTypeMenu", method = RequestMethod.PUT)
    public ResponseEntity<?> putDinningTable(@Valid @RequestBody TypeMenu dinning, BindingResult result) {
        return typeService.putMenuType(dinning, result);
    }

}
