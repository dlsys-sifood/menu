package com.dlsys.sifood.ms.dao;

import com.dlsys.sifood.ms.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductCategoryDao extends CrudRepository<ProductCategory, Integer>, JpaSpecificationExecutor<ProductCategory> {
}
