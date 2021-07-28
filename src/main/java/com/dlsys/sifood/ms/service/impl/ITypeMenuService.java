package com.dlsys.sifood.ms.service.impl;

import com.dlsys.sifood.ms.entity.TypeMenu;
import com.dlsys.sifood.ms.models.GenericSearch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ITypeMenuService {
    public ResponseEntity<?> postMenuType(TypeMenu menu, BindingResult result);
    public ResponseEntity<?> putMenuType(TypeMenu menu, BindingResult result);
    public ResponseEntity<?> getMenuType(GenericSearch menu);
}
