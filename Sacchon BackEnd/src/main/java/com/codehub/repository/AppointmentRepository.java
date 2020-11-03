package com.codehub.repository;

import com.codehub.model.Appointment;
import com.codehub.repository.lib.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public class AppointmentRepository extends Repository<Appointment, Integer> {

    public AppointmentRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Appointment> getEntityClass() {
        return Appointment.class;
    }

    @Override
    public String getEntityClassName() {
        return Appointment.class.getName();
    }

    public List<Appointment> findByPatientID(int patientID){
        List<Appointment> aps = entityManager.createQuery("from Appointment ap WHERE   ap.patient.id= :patientID ")
                .setParameter("patientID", patientID)
                .getResultList();
        return aps;
    }
    public List<Appointment> findByDoctorID(int doctorID){
        List<Appointment> aps = entityManager.createQuery("from Appointment ap WHERE   ap.doctor.id= :doctorID ")
                .setParameter("doctorID", doctorID)
                .getResultList();
        return aps;
    }
    public List<Appointment> findByPatientDoctorID(int patientID,int doctorID){
        List<Appointment> aps = entityManager.createQuery("from Appointment ap WHERE ap.doctor.id= :doctorID AND ap.patient.id =:patientID")
                .setParameter("doctorID", doctorID)
                .setParameter("patientID", patientID)
                .getResultList();
        return aps;
    }
}
