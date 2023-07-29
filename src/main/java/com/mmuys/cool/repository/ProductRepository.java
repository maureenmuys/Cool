package com.mmuys.cool.repository;

import com.mmuys.cool.model.Category;
import com.mmuys.cool.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
