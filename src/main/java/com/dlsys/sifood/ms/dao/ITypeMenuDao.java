package com.dlsys.sifood.ms.dao;

import com.dlsys.sifood.ms.entity.TypeMenu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeMenuDao extends CrudRepository<TypeMenu, Integer>, JpaSpecificationExecutor<TypeMenu> {
}
