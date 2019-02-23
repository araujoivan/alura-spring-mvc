/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.system.validation;

import br.com.system.model.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author eder
 */
public class ProductValidation implements Validator {

    final static String FIELD_REQUIRED = "field.required";
    
    @Override
    public boolean supports(Class<?> type) {
        return Product.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        
        ValidationUtils.rejectIfEmpty(errors, "title", FIELD_REQUIRED);
        ValidationUtils.rejectIfEmpty(errors, "description", FIELD_REQUIRED);
        
        final Product product = (Product) o;
        
        if(product.getPages() < 0 ) {
            errors.rejectValue("pages", FIELD_REQUIRED);
        }
        
    }
    
}
