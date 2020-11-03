package com.codehub.resource.impl;

import com.codehub.RestApplication;
import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.Doctor;
import com.codehub.model.Patient;
import com.codehub.repository.DoctorRepository;
import com.codehub.repository.PatientRepository;
import com.codehub.representation.PatientRepresentation;
import com.codehub.resource.PatientResource;
import com.codehub.resource.util.ResourceUtils;
import com.codehub.security.CustomRole;
import org.restlet.engine.Engine;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class PatientResourceImpl extends ServerResource implements PatientResource {

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private EntityManager em;
    private int id;


    public static final Logger LOGGER = Engine.getLogger(RestApplication.class); //NA TO SBHSW META

    @Override
    protected void doInit(){
        try {
            em = JpaUtil.getEntityManager();
            patientRepository = new PatientRepository(em);
            doctorRepository = new DoctorRepository(em);
            id = Integer.parseInt(getAttribute("id"));

        }
        catch(Exception ex){

            throw new ResourceException(ex);
        }

    }

    @Override
    public PatientRepresentation getPatient() throws NotFoundException{
        //List<String> roles = new ArrayList<>();
        //POIOI MPOROUN NA PAROYN TA DEDOMENA (ENA REPRESENATION APO ENAN ASTHENI
        // (TO REPRESENTATION AYTHO MPOREI NA EINAI KAI KAINOYRGIO)
        //roles.add(CustomRole.ROLE_PATIENT.getRoleName());
        //roles.add(CustomRole.ROLE_ADMIN.getRoleName());
        //ResourceUtils.checkRole(this, roles);

        Optional<Patient> patient = patientRepository.findById(id);
        setExisting(patient.isPresent());
        if (!patient.isPresent())  throw new NotFoundException("Patient is not found");

        PatientRepresentation patientRepresentation = PatientRepresentation.getPatientRepresentation(patient.get());

        return patientRepresentation;
    }

    @Override
    public void remove() throws NotFoundException {
        patientRepository.deleteById(id);

    }

    @Override
    public PatientRepresentation store(PatientRepresentation patientReprIn) throws NotFoundException, BadEntityException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");

        Optional <Patient> patientOpt = patientRepository.findById(id);


        if (!patientOpt.isPresent()) throw new NotFoundException("Patient not found");
        Patient patient = patientOpt.get();

        String oldUserNameForCheck = patient.getUsername();

        /* First check username */
        patient.setUsername(patientReprIn.getUsername());
        if(!patientRepository.save(patient).isPresent()) {
            PatientRepresentation patientRepresentation = PatientRepresentation.getPatientRepresentation(patient);
            patient.setUsername(oldUserNameForCheck); /* Reset the value in the database */
            patientRepresentation.setUsername(oldUserNameForCheck);
            LOGGER.info(patientRepresentation.getName());
            return patientRepresentation;
        }


        if (patientReprIn.getName() != null)
            patient.setName(patientReprIn.getName());
        if (patientReprIn.getUsername() != null)
            patient.setUsername(patientReprIn.getUsername());
        if(patientReprIn.getPassword() != null)
            patient.setPassword(patientReprIn.getPassword());
        if(patientReprIn.getDob() != null)
            patient.setDob( LocalDate.parse(patientReprIn.getDob(), formatter));
        patient.setHeight(patientReprIn.getHeight());
        patient.setWeight(patientReprIn.getWeight());
        patient.setGender(patientReprIn.getGender());
        //patient.setActive(patientReprIn.isActive());
        if (patient.getHistory() != null)
            patient.setHistory(patientReprIn.getHistory());

        int givenID = patientReprIn.getDoctorID();
        /* The representation does not have any doctor information for the patient */
        if (givenID != 0) {

            Optional<Doctor> doctorOpt = doctorRepository.findById(givenID);
            if (!doctorOpt.isPresent()) {
                return null; //TO JSON dinei id giatrou pou den yparxei
            } //throw new NotFoundException("Doctor not found");
            Doctor doctor = doctorOpt.get();
            patient.setDoctor(doctor);

        }

        patientRepository.save(patient);
        return PatientRepresentation.getPatientRepresentation(patient);

    }

    @Override
    protected  void doRelease(){

        em.close();
    }

}
