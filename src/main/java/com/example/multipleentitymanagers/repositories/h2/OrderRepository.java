package com.example.multipleentitymanagers.repositories.h2;

import com.example.multipleentitymanagers.models.h2.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrderRepository {

    @PersistenceContext(unitName = "H2")
    private EntityManager entityManager;

    @Transactional(value = "h2TransactionManager")
    public void save(Order user){
        entityManager.persist(user);
    }

    @Transactional(value = "h2TransactionManager")
    public void deleteAll(){
        entityManager.createNativeQuery("DELETE FROM TBL_Order").executeUpdate();
    }
}
