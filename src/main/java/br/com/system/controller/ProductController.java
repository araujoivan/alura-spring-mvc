/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.system.controller;

import br.com.system.dao.ProductDAO;
import br.com.system.infra.FileSaver;
import br.com.system.model.PriceType;
import br.com.system.model.Product;
import br.com.system.validation.ProductValidation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author eder
 */
@Controller
@RequestMapping("/products")
// create one bean for each request
public class ProductController {
    
    @Autowired
    private ProductDAO productDAO;
    
    @Autowired
    private FileSaver fileSaver;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new ProductValidation());
    }
    
    @RequestMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") Integer id) {
        
        final ModelAndView model = new ModelAndView("products/details");
        
        Product product  = productDAO.findById(id);
        
        model.addObject("product", product);
        
        
        return model;
        
    }
    
    // BindingResult must come right after product.
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(MultipartFile summary, @Valid Product product, BindingResult resul, RedirectAttributes attributes) {                
        
        if(resul.hasErrors()) {
            return form(product);
        }
        
        final String path = fileSaver.write("summaryfolder", summary);
        
        product.setSummaryPath(path);
        
        productDAO.save(product);
        
        final ModelAndView model = new ModelAndView("redirect:products");
        
        // Flash attributes keep the atribute into one request
        // Ideal for redirects.
        attributes.addFlashAttribute("message", "Product " + product.getTitle() + " added succesfully!");
        
        // after a post always redirect "get"   302
        return model;
    }
    
    @RequestMapping("/form")
    public ModelAndView form(Product product) {
        
        final ModelAndView model = new ModelAndView("products/form");
       
        model.addObject("types", PriceType.values());
        
        return model;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list() {
        
        final ModelAndView model = new ModelAndView("products/list");
        
        List<Product> products = productDAO.list();
        
        model.addObject("products", products);
        
        return model;
    }
}
