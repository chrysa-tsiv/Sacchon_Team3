package com.codehub.repository;

import com.codehub.RestApplication;
import com.codehub.model.Doctor;
import com.codehub.model.Patient;
import com.codehub.repository.lib.Repository;
import org.restlet.engine.Engine;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class DoctorRepository extends Repository<Doctor, Integer> {

    public static final Logger LOGGER = Engine.getLogger(RestApplication.class); //NA TO SBHSW META

    public DoctorRepository(EntityManager entityManager) {
        super(entityManager);
    }



    @Override
    public Class<Doctor> getEntityClass() {
        return Doctor.class;
    }

    @Override
    public String getEntityClassName() {
        return Doctor.class.getName();
    }

    public List <Doctor> findByCredentials(String username, String password){
        List <Doctor> ps = entityManager.createQuery("from Doctor d WHERE d.username= :username AND d.password= :password")
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();
        return ps;
    }

    public List <Doctor> findByUsername(String username){
        List <Doctor> ps = entityManager.createQuery("from Doctor d WHERE d.username= :username")
                .setParameter("username", username)
                .getResultList();
        return ps;
    }

    public List <Doctor> findByActivity(int activityValue){
        List<Doctor> ps;
        Integer int1 = new Integer(activityValue); //NA TO SBHSW META
        LOGGER.info("Repos:" + activityValue);
        if (activityValue == 0) {
            ps = entityManager.createQuery("from Doctor d WHERE  d.active= FALSE")
                    .getResultList();
        }
        else{
            ps = entityManager.createQuery("from Doctor d WHERE  d.active= TRUE")
                    .getResultList();
        }
        return ps;

    }


}
