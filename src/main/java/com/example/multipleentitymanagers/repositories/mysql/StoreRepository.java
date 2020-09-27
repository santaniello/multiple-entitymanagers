package com.example.multipleentitymanagers.repositories.mysql;

import com.example.multipleentitymanagers.models.mysql.Store;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class StoreRepository {
    @PersistenceContext(unitName = "MYSQL")
    private EntityManager entityManager;

    @Transactional(value = "mysqlTransactionManager")
    public void save(Store store){
        entityManager.persist(store);
    }

    @Transactional(value = "mysqlTransactionManager")
    public void deleteAll(){
        entityManager.createNativeQuery("DELETE FROM TBL_Store").executeUpdate();

    }
}
