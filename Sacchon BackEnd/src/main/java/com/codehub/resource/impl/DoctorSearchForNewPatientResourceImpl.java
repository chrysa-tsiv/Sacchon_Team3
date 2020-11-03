package com.codehub.resource.impl;

import com.codehub.RestApplication;
import com.codehub.exceptions.NotFoundException;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.Doctor;
import com.codehub.model.Patient;
import com.codehub.repository.DoctorRepository;
import com.codehub.repository.PatientRepository;
import com.codehub.representation.PatientRepresentation;
import com.codehub.resource.DoctorNeedConsultResource;
import com.codehub.resource.DoctorSearchForNewPatientResource;
import org.restlet.engine.Engine;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class DoctorSearchForNewPatientResourceImpl extends ServerResource implements DoctorSearchForNewPatientResource {

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private EntityManager em;
    //private int id;

    public static final Logger LOGGER = Engine.getLogger(RestApplication.class); //NA TO SBHSW META

    @Override
    protected void doInit(){
        try {
            em = JpaUtil.getEntityManager();
            patientRepository = new PatientRepository(em);
            doctorRepository = new DoctorRepository(em);
            //id = Integer.parseInt(getAttribute("id"));
        }
        catch(Exception ex){

            throw new ResourceException(ex);
        }

    }
    @Override
    public List<PatientRepresentation> getNewPatients() throws NotFoundException {
        //Optional<Doctor> doctorOpt = doctorRepository.findById(id);
        //if (!doctorOpt.isPresent()) throw new NotFoundException("Doctor not found");
        //Doctor doctor = doctorOpt.get();

        List<Patient> patients = patientRepository.findNewPatientsForConsulting();
        List<PatientRepresentation> patientRepresentationList = new ArrayList<>();
        patients.forEach(patient -> patientRepresentationList.add(PatientRepresentation.getPatientRepresentation(patient)));
        return patientRepresentationList;
    }

    protected void doRelease(){
        em.close();
    }
}
