package com.dlsys.sifood.ms.dao;

import com.dlsys.sifood.ms.entity.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProductDao extends CrudRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
}
