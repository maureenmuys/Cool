package com.mmuys.cool.repository;

import com.mmuys.cool.model.Category;
import com.mmuys.cool.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory_id(int id);

}
