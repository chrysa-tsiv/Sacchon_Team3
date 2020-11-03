package com.codehub.repository;


import com.codehub.model.ChiefAdministrator;
import com.codehub.repository.lib.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public class ChiefAdministratorRepository extends Repository<ChiefAdministrator, Integer> {

    public ChiefAdministratorRepository(EntityManager entityManager) {
        super(entityManager); }

    @Override
    public Class<ChiefAdministrator> getEntityClass() {
        return ChiefAdministrator.class;
    }

    @Override
    public String getEntityClassName() {
        return ChiefAdministrator.class.getName();
    }

    public List<ChiefAdministrator> findByCredentials(String username, String password){
        List <ChiefAdministrator> ps = entityManager.createQuery("from ChiefAdministrator c WHERE c.username= :username AND c.password= :password")
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();
        return ps;
    }

}

