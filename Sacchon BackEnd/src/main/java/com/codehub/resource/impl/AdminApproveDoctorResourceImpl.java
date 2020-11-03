package com.codehub.resource.impl;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.Doctor;
import com.codehub.model.Patient;
import com.codehub.repository.DoctorRepository;
import com.codehub.repository.PatientRepository;
import com.codehub.representation.DoctorRepresentation;
import com.codehub.resource.AdminApproveDoctorResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

public class AdminApproveDoctorResourceImpl extends ServerResource implements AdminApproveDoctorResource {

    private DoctorRepository doctorRepository;
    private EntityManager em;
    private String username;

    @Override
    protected void doInit(){

        try {
            em = JpaUtil.getEntityManager();
            doctorRepository = new DoctorRepository(em);
            username = getAttribute("username");
        }
        catch(Exception ex){

            throw new ResourceException(ex);
        }

    }

    @Override
    public DoctorRepresentation store(DoctorRepresentation doctorRepresentation) throws BadEntityException, NotFoundException {



        List <Doctor> doctorReturn = doctorRepository.findByUsername(username);
        if(doctorReturn.size() == 0){
            throw new NotFoundException("Doctor with that username not found");
        }

        Doctor doctor = doctorReturn.get(0);
        doctor.setActive(true);
        doctorRepository.save(doctor);

        return  DoctorRepresentation.getDoctorRepresentation(doctor);
    }

    protected void doRelease(){
        em.close();
    }
}
