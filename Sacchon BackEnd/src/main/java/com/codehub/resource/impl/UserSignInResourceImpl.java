package com.codehub.resource.impl;

import com.codehub.exceptions.BadEntityException;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.ChiefAdministrator;
import com.codehub.model.Doctor;
import com.codehub.model.Patient;
import com.codehub.repository.ChiefAdministratorRepository;
import com.codehub.repository.DoctorRepository;
import com.codehub.repository.PatientRepository;
import com.codehub.representation.UserRepresentation;
import com.codehub.resource.UserSignInResource;
import com.codehub.security.CustomRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

public class UserSignInResourceImpl extends ServerResource implements UserSignInResource {

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private ChiefAdministratorRepository chiefAdministratorRepository;
    private EntityManager em;

    @Override
    protected void doInit(){
        try {
            em = JpaUtil.getEntityManager();
            patientRepository = new PatientRepository(em);
            doctorRepository = new DoctorRepository(em);
            chiefAdministratorRepository = new ChiefAdministratorRepository(em);
        }
        catch(Exception ex){

            throw new ResourceException(ex);
        }

    }


    @Override
    public UserRepresentation add(UserRepresentation userReprIn) throws BadEntityException {

        UserRepresentation userRepresentation = new UserRepresentation();

        if (userReprIn == null) throw new BadEntityException("Null representation file");

        List <Patient> patientCredential = patientRepository.findByCredentials(userReprIn.getUsername(),userReprIn.getPassword());
        if (patientCredential.size() == 0){

            /* Search to the doctor's database */
            List <Doctor> doctorCredential = doctorRepository.findByCredentials(userReprIn.getUsername(),userReprIn.getPassword());

            if (doctorCredential.size() == 0){

                /* Search to the admin table */
                List <ChiefAdministrator> adminCredential = chiefAdministratorRepository.findByCredentials(userReprIn.getUsername(),userReprIn.getPassword());

                if (adminCredential.size() == 0){
                    /*Role == null marks that the user didn't found */
                    userRepresentation.setRole(null);
                }
                else{
                    userRepresentation.setUsername(adminCredential.get(0).getUsername());
                    userRepresentation.setPassword(adminCredential.get(0).getPassword());
                    userRepresentation.setId(adminCredential.get(0).getId());
                    userRepresentation.setRole(CustomRole.ROLE_ADMIN.getRoleName());
                }
            }
            else{
                userRepresentation.setUsername(doctorCredential.get(0).getUsername());
                userRepresentation.setPassword(doctorCredential.get(0).getPassword());
                userRepresentation.setId(doctorCredential.get(0).getId());
                userRepresentation.setRole(CustomRole.ROLE_DOCTOR.getRoleName());

            }

        }
        /* Found username and password in the Patient database */
        else{
            userRepresentation.setUsername(patientCredential.get(0).getUsername());
            userRepresentation.setPassword(patientCredential.get(0).getPassword());
            userRepresentation.setId(patientCredential.get(0).getId());
            userRepresentation.setRole(CustomRole.ROLE_PATIENT.getRoleName());

        }
        return userRepresentation;


    }



    protected void doRelease(){
        em.close();
    }
}
