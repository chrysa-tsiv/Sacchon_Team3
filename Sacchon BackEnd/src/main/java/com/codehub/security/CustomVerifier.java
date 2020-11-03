package com.codehub.security;

import com.codehub.RestApplication;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.ChiefAdministrator;
import com.codehub.model.Doctor;
import com.codehub.model.Patient;
import com.codehub.repository.ChiefAdministratorRepository;
import com.codehub.repository.DoctorRepository;
import com.codehub.repository.PatientRepository;
import org.restlet.Request;
import org.restlet.engine.Engine;
import org.restlet.resource.ResourceException;
import org.restlet.security.Role;
import org.restlet.security.SecretVerifier;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

public class CustomVerifier extends SecretVerifier {

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private ChiefAdministratorRepository chiefAdministratorRepository;
    private EntityManager em;


    @Override
    public int verify(String identifier, char[] secret) throws IllegalArgumentException {

        String inputPassword = String .valueOf(secret);

        try {
            em = JpaUtil.getEntityManager();
            patientRepository = new PatientRepository(em);
            doctorRepository = new DoctorRepository(em);
            chiefAdministratorRepository = new ChiefAdministratorRepository(em);
        }
        catch(Exception ex){

            throw new ResourceException(ex);
        }

        List<Patient> patientCredential = patientRepository.findByCredentials(identifier,inputPassword);
        if (patientCredential.size() == 0) {
            /* Search to the Doctor table */
            List <Doctor> doctorCredential = doctorRepository.findByCredentials(identifier,inputPassword);
            if(doctorCredential.size() == 0) {
                /* Search to the Admin table */
                List <ChiefAdministrator> adminCredential = chiefAdministratorRepository.findByCredentials(identifier,inputPassword);
                if (adminCredential.size() == 0){
                    return SecretVerifier.RESULT_INVALID;
                }
                else{
                    Request request = Request.getCurrent();
                    request.getClientInfo().getRoles().add(new Role(CustomRole.ROLE_ADMIN.getRoleName()));
                    return SecretVerifier.RESULT_VALID;
                }

            }
            else{
                Request request = Request.getCurrent();
                request.getClientInfo().getRoles().add(new Role(CustomRole.ROLE_DOCTOR.getRoleName()));
                return SecretVerifier.RESULT_VALID;
            }
        }
        /* We found username in patient table */
        else{
            Request request = Request.getCurrent();
            request.getClientInfo().getRoles().add(new Role(CustomRole.ROLE_DOCTOR.getRoleName()));
            return SecretVerifier.RESULT_VALID;

        }




    }



    /*
    public int verify(String identifier, char[] secret)
            throws IllegalArgumentException {
        LOGGER.info("Identifier:" + identifier);
        ApplicationUserPersistence applicationUserPersistence = ApplicationUserPersistence.getApplicationUserPersistence();
        ApplicationUser user = null;
        try {
            user = applicationUserPersistence.findById(identifier);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //user contains both user hints and roles
        if (user!=null
                && compare(user.getPassword().toCharArray(), secret)) {

            Request request = Request.getCurrent();
            request.getClientInfo().getRoles().add(new Role(user.getRole().getRoleName()));
            return SecretVerifier.RESULT_VALID;
        } else {
            return SecretVerifier.RESULT_INVALID;
        }

    }*/
}
