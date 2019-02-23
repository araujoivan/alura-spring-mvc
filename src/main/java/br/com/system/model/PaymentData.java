/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.system.model;

import java.math.BigDecimal;

/**
 *
 * @author eder
 */
public class PaymentData {
    
    private BigDecimal value;

    public PaymentData(BigDecimal value) {
        this.value = value;
    }
    
    public BigDecimal getValue() {
        return value;
    }
     
}
