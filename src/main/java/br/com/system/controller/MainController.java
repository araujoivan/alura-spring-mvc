/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



/**
 *
 * @author eder
 */

@RestController
public class MainController {
    
    @RequestMapping("/")
    public ModelAndView index() {
        
        final ModelAndView model = new ModelAndView("home");
        
        return model;
    }
    
}
