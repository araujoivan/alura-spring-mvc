/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.system.configuration;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author eder
 */
public class SpringServletConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppWebConfiguration.class, JPAConfiguration.class};
    }

    // all requests bellow / will be managed by spring
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    
    //dealing with encoding
    // by default mysql creates a database in UTF-8
    @Override
    protected Filter[] getServletFilters() {
        
        CharacterEncodingFilter encoding = new CharacterEncodingFilter();
        
        encoding.setEncoding("UTF-8");

        return new Filter[]{encoding};     
    }

    // for upload files
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement(""));
    }

}
