/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.system.configuration;

import br.com.system.controller.MainController;
import br.com.system.dao.ProductDAO;
import br.com.system.infra.FileSaver;
import br.com.system.model.ShopCart;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author eder
 */
@EnableWebMvc
//basePackageClasses indicates the package to be scanning throught the class reference
@ComponentScan(basePackageClasses = {MainController.class, ProductDAO.class, FileSaver.class, ShopCart.class})
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

    @Bean // all classes managed by spring is a bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        
        // where we can put our jsp views
        resolver.setPrefix("/WEB-INF/views/");
        // to guarantee the needless of using extensions
        resolver.setSuffix(".jsp");
        
        // All beans will be available in jsp files.
        // resolver.setExposeContextBeansAsAttributes(Boolean.TRUE);
        
        // expose just beans declares into list  
        resolver.setExposedContextBeanNames("shopCart");
        
        return resolver;
    }
    
    
    // When using a method to define a Bean, we must follow this convention and name method like the bean name
    // or define the bean name into Bean("beanName")
    @Bean
    public MessageSource messageSource() {
        
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        
        // points to our message.properties file
        messageSource.setBasename("/WEB-INF/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(1); // interval in seconds to reload message file
        
        return messageSource;
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    public FormattingConversionService mvcConversionService() {
        
        final DefaultFormattingConversionService format = new DefaultFormattingConversionService();
        
        final DateFormatterRegistrar registrar = new DateFormatterRegistrar();
        
        registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
        
        registrar.registerFormatters(format);
        
        return format;
        
    }
    
    // Accepting uploads
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    // Spring MVC deny the access to resource folder, for this reason Tomcat is not able to load CSS files from this folder
    // To grant access is necessary overwrite the method configureDefaultServletHandling
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    } 
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/"); 
    }
}