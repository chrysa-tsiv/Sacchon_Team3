package com.codehub.resource.impl;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.Doctor;
import com.codehub.model.Patient;
import com.codehub.repository.DoctorRepository;
import com.codehub.repository.PatientRepository;
import com.codehub.representation.DoctorRepresentation;
import com.codehub.resource.DoctorAssignNewPatient;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class DoctorAssignNewPatientImpl extends ServerResource implements DoctorAssignNewPatient {

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private EntityManager em;
    private String username;

    @Override
    protected void doInit(){
        try {
            em = JpaUtil.getEntityManager();
            patientRepository = new PatientRepository(em);
            doctorRepository = new DoctorRepository(em);
            username = getAttribute("username");
        }
        catch(Exception ex){

            throw new ResourceException(ex);
        }

    }


    @Override
    public DoctorRepresentation store(DoctorRepresentation doctorRepresentation) throws BadEntityException, NotFoundException {

        List<Patient> patientReturn = patientRepository.findByUsername(username);
        if (patientReturn.size() == 0){
            throw new NotFoundException("Patient with that username not found");
        }

        Patient patient = patientReturn.get(0);

        /* The patient is not available or inactive */
        if (!patient.isAvailable() || !patient.isActive()){
            return null;
        }

        List <Doctor> doctorReturn = doctorRepository.findByUsername(doctorRepresentation.getUsername());
        if(doctorReturn.size() == 0){
            throw new NotFoundException("Doctor with that username not found");
        }

        Doctor doctor = doctorReturn.get(0);

        patient.setDoctor(doctor);
        patient.setAvailable(false);
        doctor.setNumOfPatients(doctor.getNumOfPatients() + 1);

        patientRepository.save(patient);
        doctorRepository.save(doctor);

        return  DoctorRepresentation.getDoctorRepresentation(doctor);
    }


    protected void doRelease(){
        em.close();
    }




}
