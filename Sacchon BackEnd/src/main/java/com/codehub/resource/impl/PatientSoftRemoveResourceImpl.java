package com.codehub.resource.impl;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.Doctor;
import com.codehub.model.Patient;
import com.codehub.repository.DoctorRepository;
import com.codehub.repository.PatientRepository;
import com.codehub.representation.PatientRepresentation;
import com.codehub.resource.PatientSoftRemoveResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class PatientSoftRemoveResourceImpl extends ServerResource implements PatientSoftRemoveResource {

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private EntityManager em;
    private int id;

    @Override
    protected void doInit(){
        try {
            em = JpaUtil.getEntityManager();
            doctorRepository = new DoctorRepository(em);
            patientRepository = new PatientRepository(em);
            id = Integer.parseInt(getAttribute("id"));
        }
        catch(Exception ex){

            throw new ResourceException(ex);
        }
    }

    @Override
    public PatientRepresentation store(PatientRepresentation patientRepresentation) throws BadEntityException, NotFoundException {
        Optional<Patient> patientOpt = patientRepository.findById(id);
        if (!patientOpt.isPresent()) throw new NotFoundException("Patient not found");
        Patient patient = patientOpt.get();
        List<Doctor> doctors = doctorRepository.findAll();
        doctors.forEach(doctor -> {
            if (doctor.getPatients().contains(patient)) {
                doctor.getPatients().remove(patient);
                if (doctor.getPatients().size()>=0) {
                    doctor.setNumOfPatients(doctor.getNumOfPatients()-1);
                }
                doctorRepository.save(doctor);
                }
        });
        patient.setActive(false);
        patient.setAvailable(false);
        patient.setDoctor(null);
        patientRepository.save(patient);
        return PatientRepresentation.getPatientRepresentation(patient);
    }

    protected void doRelease(){
        em.close();
    }
}
