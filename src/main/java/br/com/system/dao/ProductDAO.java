/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.system.dao;

import br.com.system.model.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eder
 */

@Transactional  //
@Repository  //Repository extends from Component. It is a semantic
public class ProductDAO {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public void save(Product product) {
        
        entityManager.persist(product);
    } 

    public List<Product> list() {
                
        return entityManager.createQuery("select p from Product p", Product.class).getResultList();
    }

    public Product findById(Integer id) {
        return entityManager.createQuery("select distinct(p) from Product p join fetch p.prices prices where p.id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
