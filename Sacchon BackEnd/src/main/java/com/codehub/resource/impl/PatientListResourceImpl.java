package com.codehub.resource.impl;

import com.codehub.RestApplication;
import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.Patient;
import com.codehub.repository.PatientRepository;
import com.codehub.representation.PatientRepresentation;
import com.codehub.resource.PatientListResource;
import com.codehub.resource.util.ResourceUtils;
import com.codehub.security.CustomRole;
import org.restlet.engine.Engine;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class PatientListResourceImpl extends ServerResource implements PatientListResource {

    private PatientRepository patientRepository;
    private EntityManager em;

    public static final Logger LOGGER = Engine.getLogger(RestApplication.class); //NA TO SBHSW META
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");


    @Override
    protected void doInit(){
        try {
            em = JpaUtil.getEntityManager();
            patientRepository = new PatientRepository(em);
        }
        catch(Exception ex){

            throw new ResourceException(ex);
        }

    }


    @Override
    public PatientRepresentation add(PatientRepresentation patientIn) throws BadEntityException {

        /* Validation check */
        if (patientIn == null) {
            throw new BadEntityException("Patient null representation error");
        }
        if (patientIn.getUsername().equals("admin")){
            throw new BadEntityException("Invalid doctor username error");
        }

        //WE WILL MODIFY THIS WITH BETTER PATTERNS
        if (patientIn.getPassword().length() < 8){
            throw new BadEntityException("Password must have at least 8 characters");
        }


        Patient patient = PatientRepresentation.getPatient(patientIn);
        patient.setSignUpDate( java.time.LocalDate.now());
        patient.setActive(true);
        patient.setAvailable(true); /* As he sign up, first time*/

        if(!patientRepository.save(patient).isPresent()) {
            patient.setUsername(null);
        }

        return PatientRepresentation.getPatientRepresentation(patient);
    }

 //   @Override
 //   public List<PatientRepresentation> getPatients() throws NotFoundException {
 //       return null;
//    }

    @Override
    public List<PatientRepresentation> getPatients() throws NotFoundException {
        //List<String> roles = new ArrayList<>();
        //roles.add(CustomRole.ROLE_PATIENT.getRoleName());
        //roles.add(CustomRole.ROLE_DOCTOR.getRoleName());
        //ResourceUtils.checkRole(this, roles);
        try {
            int availablePatients = parseInt(getQueryValue("available"));
            if (availablePatients == 0) throw new Exception();
            List<Patient> patients = patientRepository.findByAvailability(1);
            List<PatientRepresentation> patientRepresentationList = new ArrayList<>();
            patients.forEach(patient -> patientRepresentationList.add(PatientRepresentation.getPatientRepresentation(patient)));
            return patientRepresentationList;

        } catch (Exception e) {
        }
        try {
            int doctorID = parseInt(getQueryValue("doctorID"));
            if (doctorID== 0) throw new Exception();
            List<Patient> patients = patientRepository.findByDoctorID(doctorID);
            List<PatientRepresentation> patientRepresentationList = new ArrayList<>();
            patients.forEach(patient -> patientRepresentationList.add(PatientRepresentation.getPatientRepresentation(patient)));
            return patientRepresentationList;
        } catch (Exception e) {
        }

        try {
            String inputUsername = getQueryValue("username");
            List<Patient> patients = patientRepository.findByUsername(inputUsername);
            List<PatientRepresentation> patientRepresentationList = new ArrayList<>();
            patients.forEach(patient -> patientRepresentationList.add(PatientRepresentation.getPatientRepresentation(patient)));
            return patientRepresentationList;
        } catch (Exception e) {
        }






        try {
            int availableValue = parseInt(getQueryValue("available"));
            if (!(availableValue == 0 || availableValue == 1))
                return null; //throw new Exception();
            List<Patient> patients = patientRepository.findByAvailability(availableValue);

            List<PatientRepresentation> patientRepresentationList = new ArrayList<>();
            patients.forEach(patient -> patientRepresentationList.add(PatientRepresentation.getPatientRepresentation(patient)));
            return patientRepresentationList;
        } catch (Exception e) {

        }
        List<Patient> patients = patientRepository.findAll();
        List<PatientRepresentation> patientRepresentationList = new ArrayList<>();
        patients.forEach(patient -> patientRepresentationList.add(PatientRepresentation.getPatientRepresentation(patient)));
        return patientRepresentationList;
    }
    protected void doRelease(){
        em.close();
    }
}
