/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.system.controller;

import br.com.system.dao.ProductDAO;
import br.com.system.model.CarItem;
import br.com.system.model.PriceType;
import br.com.system.model.Product;
import br.com.system.model.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author eder
 */
@Controller
@RequestMapping("/cart")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ShopCartController {
    
    @Autowired
    private ProductDAO productDAO;
    
    @Autowired
    private ShopCart shopCar;
    
    @RequestMapping("/add")
    public ModelAndView add(Integer productId, PriceType priceType) {
        
        final ModelAndView model = new ModelAndView("redirect:/cart");
        
        final CarItem carItem = createCarItem(productId, priceType);
        
        shopCar.add(carItem);
        
        return model;
        
    }

    private CarItem createCarItem(Integer productId, PriceType priceType) {
        
        final Product product = productDAO.findById(productId);
        
        return new CarItem(product, priceType);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView items() {
        return new ModelAndView("cart/items");
    }
    
    @RequestMapping("/remove")
    public ModelAndView remove(Integer productId, PriceType priceType) {
        
        shopCar.remove(productId, priceType);
        
        return new ModelAndView("redirect:/cart");
    }
    
}
