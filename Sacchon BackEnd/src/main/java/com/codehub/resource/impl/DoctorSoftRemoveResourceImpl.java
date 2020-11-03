package com.codehub.resource.impl;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.Doctor;
import com.codehub.model.Patient;
import com.codehub.repository.DoctorRepository;
import com.codehub.repository.PatientRepository;
import com.codehub.representation.DoctorRepresentation;
import com.codehub.resource.DoctorSoftRemoveResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class DoctorSoftRemoveResourceImpl extends ServerResource implements DoctorSoftRemoveResource {

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
    public DoctorRepresentation store(DoctorRepresentation doctorRepresentation) throws BadEntityException, NotFoundException {
        Optional<Doctor> doctorOpt = doctorRepository.findById(id);
        if (!doctorOpt.isPresent()) throw new NotFoundException("Doctor not found");
        Doctor doctor = doctorOpt.get();
        List<Patient> patients = patientRepository.findByDoctorID(id);

        patients.forEach(patient -> {
                                patient.setDoctor(null);
                                patient.setAvailable(true);
                                patientRepository.save(patient);

        });
        doctor.setActive(false);
        doctor.setNumOfPatients(0);
        doctorRepository.save(doctor);

        return DoctorRepresentation.getDoctorRepresentation(doctor);

    }

    protected void doRelease(){
        em.close();
    }
}
