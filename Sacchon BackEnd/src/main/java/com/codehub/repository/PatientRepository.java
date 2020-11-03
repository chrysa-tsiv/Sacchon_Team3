package com.codehub.repository;

import com.codehub.model.Measurement;
import com.codehub.model.Patient;
import com.codehub.repository.lib.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class PatientRepository extends Repository<Patient, Integer> {

    public PatientRepository(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public Class<Patient> getEntityClass() {
        return Patient.class;
    }

    @Override
    public String getEntityClassName() {
        return Patient.class.getName();
    }



    public List<Patient>  findByDoctorID(int doctorID){
        List<Patient> ps = entityManager.createQuery("from Patient p WHERE   p.doctor.id= :doctorID ")
                .setParameter("doctorID", doctorID)
                .getResultList();
        return ps;
    }

    public List <Patient> findByCredentials(String username, String password){
        List <Patient> ps = entityManager.createQuery("from Patient p WHERE  p.username= :username AND p.password= :password")
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();
        return ps;
    }

    public List <Patient> findByAvailability(int availabilityValue){
        List<Patient> ps;
        if (availabilityValue == 0) {
            ps = entityManager.createQuery("from Patient p WHERE  p.available= FALSE")
                    .getResultList();
        }
        else{
            ps = entityManager.createQuery("from Patient p WHERE  p.available= TRUE")
                    .getResultList();
        }
        return ps;

    }
/*
    public List <Patient> findNeedConsultationByDoctor(int doctorID){
        List<Patient> ps = entityManager.createQuery("from Patient p WHERE   p.doctor.id= :doctorID AND p.needConsulting=1 AND p.active=1")
                .setParameter("doctorID", doctorID)
                .getResultList();
        return ps;

    }
*/
    public List <Patient> findNewPatientsForConsulting(){
        List<Patient> ps = entityManager.createQuery("from Patient p WHERE p.available= true ").getResultList();
        return ps;
    }

    public List <Patient> findByUsername(String username){
        List <Patient> ps = entityManager.createQuery("from Patient p WHERE p.username= :username")
                .setParameter("username", username)
                .getResultList();
        return ps;
    }
    public List <Patient> findNeedConsultationByDoctor(int doctorID){
        List<Patient> ps = entityManager.createQuery("from Patient p WHERE   p.doctor.id= :doctorID AND p.needConsulting= :TRUE")
                .setParameter("doctorID", doctorID)
                .getResultList();
        return ps;

    }


}
