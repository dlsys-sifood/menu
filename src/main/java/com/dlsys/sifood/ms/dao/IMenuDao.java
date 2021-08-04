package com.dlsys.sifood.ms.dao;

import com.dlsys.sifood.ms.entity.Menu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IMenuDao extends CrudRepository<Menu, UUID>, JpaSpecificationExecutor<Menu> {
}
