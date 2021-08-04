package com.dlsys.sifood.ms.service;

import com.dlsys.sifood.ms.entity.Menu;
import com.dlsys.sifood.ms.models.MenuSearch;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public interface IMenuService {
    public ResponseEntity<?> postMenu(Menu menu, BindingResult result);
    public ResponseEntity<?> putMenu(Menu menu, BindingResult result);
    public ResponseEntity<?> getMenu(MenuSearch menu);
}
