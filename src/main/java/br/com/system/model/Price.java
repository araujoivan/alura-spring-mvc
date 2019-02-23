/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.system.model;

import java.math.BigDecimal;
import javax.persistence.Embeddable;

/**
 *
 * @author eder
 */

// this price is part of product. needs a embeddable annotation
@Embeddable
public class Price {
    
    public Price() {}
    
    private BigDecimal value;
    private PriceType type;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public PriceType getType() {
        return type;
    }

    public void setType(PriceType type) {
        this.type = type;
    }
}
