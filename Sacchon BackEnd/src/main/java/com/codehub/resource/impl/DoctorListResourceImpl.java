package com.codehub.resource.impl;

import com.codehub.RestApplication;
import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.Doctor;
import com.codehub.model.Patient;
import com.codehub.repository.DoctorRepository;
import com.codehub.representation.DoctorRepresentation;
import com.codehub.representation.PatientRepresentation;
import com.codehub.resource.DoctorListResource;
import org.restlet.engine.Engine;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;


import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class DoctorListResourceImpl extends ServerResource implements DoctorListResource {

    public static final Logger LOGGER = Engine.getLogger(RestApplication.class); //NA TO SBHSW META
    private DoctorRepository doctorRepository;
    private EntityManager em;


    @Override
    protected void doInit(){
        try {
            em = JpaUtil.getEntityManager();
            doctorRepository = new DoctorRepository(em);
        }
        catch(Exception ex){

            throw new ResourceException(ex);
        }

    }


    @Override
    public DoctorRepresentation add(DoctorRepresentation doctorIn) throws BadEntityException {

        /* Validation check */
        if (doctorIn == null) {
            throw new BadEntityException("Doctor null representation error");
        }
        if (doctorIn.getUsername().equals("admin")){
            throw new BadEntityException("Invalid doctor username error");
        }

        //WE WILL MODIFY THIS WITH BETTER PATTERNS
        if (doctorIn.getPassword().length() < 8){
            throw new BadEntityException("Password must have at least 8 characters");
        }

        Doctor doctor = DoctorRepresentation.getDoctor(doctorIn);
        doctor.setActive(false);
        if(!doctorRepository.save(doctor).isPresent()) {
            doctor.setUsername(null);
        }

        return DoctorRepresentation.getDoctorRepresentation(doctor);

    }

    @Override
    public List<DoctorRepresentation> getDoctors() throws NotFoundException {


        try {
            Integer activeValue = parseInt(getQueryValue("active"));
            if (!(activeValue == 0 || activeValue == 1))
                return null; //throw new Exception();
            LOGGER.info(activeValue.toString());
            List<Doctor> doctors = doctorRepository.findByActivity(activeValue);

            List<DoctorRepresentation> doctorRepresentationList = new ArrayList<>();
            doctors.forEach(doctor -> doctorRepresentationList.add(DoctorRepresentation.getDoctorRepresentation(doctor)));
            return doctorRepresentationList;
        } catch (Exception e) {

        }


        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorRepresentation> doctorRepresentationList = new ArrayList<>();
        doctors.forEach(doctor -> doctorRepresentationList.add(DoctorRepresentation.getDoctorRepresentation(doctor)));
        return doctorRepresentationList;
    }


    protected void doRelease(){
        em.close();
    }


}
