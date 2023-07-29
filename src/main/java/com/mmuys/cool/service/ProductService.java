package com.mmuys.cool.service;


import com.mmuys.cool.model.Product;
import com.mmuys.cool.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
