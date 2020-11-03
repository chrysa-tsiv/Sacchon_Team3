package com.codehub.repository;

import com.codehub.model.Doctor;
import com.codehub.model.Measurement;
import com.codehub.model.Patient;
import com.codehub.repository.lib.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import static java.lang.Integer.parseInt;

public class MeasurementRepository extends Repository<Measurement, Integer> {

    public MeasurementRepository(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public Class<Measurement> getEntityClass() {
        return Measurement.class;
    }

    @Override
    public String getEntityClassName() {
        return Measurement.class.getName();
    }


    public int countMeasurements(int patientID ) {
        Patient t = entityManager.find(Patient.class, patientID);
        LocalDate testdate = t.getFirst_entry();
        if(testdate!=null) {
            List<Measurement> queryMeasurements = entityManager.createQuery("SELECT m FROM Measurement m WHERE patient.id = " + patientID + " AND date >='"+testdate+"'")
                    .getResultList();
            return queryMeasurements.size();
        }
        return 0;
    }

    public void updateFirstEntry(Measurement measurement ) {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("UPDATE Patient SET first_entry='"+  measurement.getDate()+"' where  id = "+measurement.getPatient().getId()).executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.clear();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Optional<Measurement> save(Measurement measurement) {
        if (measurement.getPatient()!=null) {
            if (countMeasurements(measurement.getPatient().getId()) < 1) {
                updateFirstEntry(measurement);
            }
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(measurement);
            entityManager.getTransaction().commit();
            if (measurement.getPatient()!=null) {
                updatePatientNeedConsult(measurement);
            }
            return Optional.of(measurement);

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /*
    public void updatePatientAvailability(Measurement measurement) {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");
        int patientToCheckID =measurement.getPatient().getId();
        Patient t = entityManager.find(Patient.class, patientToCheckID);
        LocalDate Today = LocalDate.now();
        LocalDate firstEntryDate = t.getFirst_entry();
        long noOfDaysBetween = ChronoUnit.DAYS.between(firstEntryDate, Today);
        if (noOfDaysBetween>8 && countMeasurements(patientToCheckID)>6){
            try {
                entityManager.getTransaction().begin();
                entityManager.createQuery("UPDATE Patient SET available='true' where id = "+measurement.getPatient().getId()).executeUpdate();
      //          entityManager.createQuery("UPDATE Patient SET first_entry="+ measurement.getDate()+" where id = "+measurement.getPatient().getId()).executeUpdate();
                entityManager.getTransaction().commit();
                entityManager.clear();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }*/

    public void updatePatientNeedConsult(Measurement measurement) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");
        int patientToCheckID =measurement.getPatient().getId();
        Patient t = entityManager.find(Patient.class, patientToCheckID);
        LocalDate Today = measurement.getDate();
        LocalDate firstEntryDate = t.getFirst_entry();
        long noOfDaysBetween = ChronoUnit.DAYS.between(firstEntryDate, Today);
        if (noOfDaysBetween>30 && countMeasurements(patientToCheckID)>25){
            try {
                entityManager.getTransaction().begin();
                entityManager.createQuery("UPDATE Patient SET needConsulting='true' where id = "+measurement.getPatient().getId()).executeUpdate();
                //          entityManager.createQuery("UPDATE Patient SET first_entry="+ measurement.getDate()+" where id = "+measurement.getPatient().getId()).executeUpdate();
                entityManager.getTransaction().commit();
                entityManager.clear();

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    public List<Measurement>  findByPatientID(int patientID){
        List<Measurement> ms = entityManager.createQuery("from Measurement m WHERE   m.patient.id= :patientID ")
                .setParameter("patientID", patientID)
                .getResultList();
        return ms;
    }

    public List<Measurement>  findByPatientUsernameandDate(String username, LocalDate start, LocalDate end) {
        List<Measurement> ms = entityManager.createQuery("from Measurement m WHERE   m.patient.username= :username AND m.date>=:start AND  m.date<=:end")
                .setParameter("username", username)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
        return ms;
    }
    public List<Measurement>  findByPatientUsernameandDate(String username, LocalDate start) {
        List<Measurement> ms = entityManager.createQuery("from Measurement m WHERE   m.patient.username= :username AND m.date>=:start")
                .setParameter("username", username)
                .setParameter("start", start)
                .getResultList();
        return ms;
    }


    public List<Measurement>  findByPatientIDandDate(int patientID, LocalDate start, LocalDate end){
        List<Measurement> ms = entityManager.createQuery("from Measurement m WHERE   m.patient.id= :patientID AND m.date>=:start AND  m.date<=:end")
                .setParameter("patientID", patientID)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
        return ms;
    }
    public List<Measurement>  findByPatientIDandDate(int patientID, LocalDate start){
        List<Measurement> ms = entityManager.createQuery("from Measurement m WHERE   m.patient.id= :patientID AND m.date>=:start")
                .setParameter("patientID", patientID)
                .setParameter("start", start)
                .getResultList();
        return ms;
    }

    public List<Measurement>  measurementExists(int patientID, LocalDate date){
        List<Measurement> ms = entityManager.createQuery("from Measurement m WHERE   m.patient.id= :patientID AND m.date=:date")
                .setParameter("patientID", patientID)
                .setParameter("date", date)
                .getResultList();
        return ms;
    }
    public List<Measurement>  findByPatientUsername(String username) {
        List<Measurement> ms = entityManager.createQuery("from Measurement m WHERE   m.patient.username= :username ")
                .setParameter("username", username)
                .getResultList();
        return ms;
    }


}
