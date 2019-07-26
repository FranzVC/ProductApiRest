package com.apirest.Repositories;

import com.apirest.Models.Product;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
