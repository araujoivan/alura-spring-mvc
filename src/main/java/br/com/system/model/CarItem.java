/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.system.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author eder
 */
public class CarItem implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Product product;
    private PriceType priceType;

    public CarItem(Product product, PriceType priceType) {

        this.priceType = priceType;
        this.product = product;
    }

    public BigDecimal getPrice() {

        return product.priceFor(priceType);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarItem other = (CarItem) obj;
        if (!Objects.equals(this.product.getId(), other.product.getId())) {
            return false;
        }
        if (this.priceType != other.priceType) {
            return false;
        }
        return true;
    }

    public BigDecimal getTotal(Integer quantity) {
        return this.getPrice().multiply(new BigDecimal(quantity));
    }

}
