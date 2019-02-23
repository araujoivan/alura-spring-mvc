/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.system.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author eder
 */

@Entity
public class Product {
    
    
    public Product() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private int pages;
    
    private Calendar publishedDate;
    
    private String summaryPath;
    
    // add the price as part of product. JPA creates an extra table
    // we dont need to have an id of prices, you dont need to use @OneToMany
    // althougt this is a one to many relationship
    @ElementCollection
    private List<Price> prices;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public Calendar getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Calendar publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSummaryPath() {
        return summaryPath;
    }

    public void setSummaryPath(String summaryPath) {
        this.summaryPath = summaryPath;
    }
    
    @Override
    public String toString() {
        return "Product{" + "title=" + title + ", description=" + description + ", pages=" + pages + '}';
    }

    public BigDecimal priceFor(PriceType priceType) {
        
        return prices.stream().filter(price -> price.getType().equals(priceType))
                .findFirst()
                .orElse(new Price()).getValue();        
    }
}
