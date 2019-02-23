/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.system.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author eder
 */
@Component
// create  bean for each web browser instance
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShopCart implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Map<CarItem, Integer> items = new LinkedHashMap();
      
    public Collection<CarItem> getItems() {
        return items.keySet();
    }
    
    public void add(CarItem item) {
        
        items.put(item, this.getQuantity(item) + 1);
    }

    public Integer getQuantity(CarItem item) {
        if(!items.containsKey(item)) {
            items.put(item, 0);
        }
        
        return items.get(item);
    }
    
    public int getQuantity() {
        return items.values().stream().reduce(0, (next, accum) -> next + accum);
    }
    
    public BigDecimal getTotal(CarItem item) {
        return item.getTotal(getQuantity(item));
    }
    
    public BigDecimal getTotal() {
        
        return items.keySet().stream()
                .map(this::getTotal)
                .reduce(BigDecimal.ZERO, (next, accum) -> next.add(accum));        
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
        final ShopCart other = (ShopCart) obj;
        if (!Objects.equals(this.items, other.items)) {
            return false;
        }
        return true;
    }

    public void remove(Integer productId, PriceType type) {
        
        final Product product = new Product();
        
        product.setId(productId);
        
        items.remove(new CarItem(product, type));
        
    }
    
    
    
}
