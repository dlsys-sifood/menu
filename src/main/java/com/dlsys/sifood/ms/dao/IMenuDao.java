package com.dlsys.sifood.ms.dao;

import com.dlsys.sifood.ms.entity.Menu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IMenuDao extends CrudRepository<Menu, UUID>, JpaSpecificationExecutor<Menu> {
}
