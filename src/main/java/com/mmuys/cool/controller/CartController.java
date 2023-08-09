package com.mmuys.cool.controller;


import com.mmuys.cool.global.GlobalData;
import com.mmuys.cool.model.Product;
import com.mmuys.cool.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.stream.DoubleStream;

@Controller
public class CartController {

    @Autowired
    ProductService productService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        GlobalData .cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }

    @GetMapping("/cart")
    public String CartGet(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        return "cart";


    }

}
