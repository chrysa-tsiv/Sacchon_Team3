package com.codehub;


import com.codehub.jpa.JpaUtil;
import com.codehub.repository.*;
import com.codehub.router.CustomRouter;
import com.codehub.security.CustomRole;
import com.codehub.security.Shield;
import com.codehub.security.cors.CustomCorsFilter;
import com.codehub.test.Test;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.Role;

import javax.persistence.EntityManager;
import java.util.logging.Logger;


public class RestApplication extends Application {

    public static final Logger LOGGER = Engine.getLogger(RestApplication.class);

    public static void main(String[] args) throws Exception {

        EntityManager em   = JpaUtil.getEntityManager();

        //Initialize repositories objects
        DoctorRepository dpr = new DoctorRepository(em);
        PatientRepository str = new PatientRepository(em);
        MeasurementRepository msm = new MeasurementRepository(em);
        AppointmentRepository apr = new AppointmentRepository(em);
        ChiefAdministratorRepository chiefrep = new ChiefAdministratorRepository(em);

        Test test = new Test(em, dpr, str, msm, apr, chiefrep);
        test.test1();

        em.close();
        Component c = new Component();
        c.getServers().add(Protocol.HTTP, 9000);
        c.getDefaultHost().attach("/app", new RestApplication());
        c.start(); // AYTO MPOREI NA DHMIOURGHSEI EXCEPTION

        LOGGER.info("Sample Web API started");
        LOGGER.info("URL: http://localhost:9000/app/patient/1");

    }

    public RestApplication(){

        setName("WebAPITutorial");
        setDescription("Full Web API tutorial");

        getRoles().add(new Role(this, CustomRole.ROLE_ADMIN.getRoleName()));
        getRoles().add(new Role(this, CustomRole.ROLE_DOCTOR.getRoleName()));
        getRoles().add(new Role(this, CustomRole.ROLE_PATIENT.getRoleName()));

    }

    @Override
    public Restlet createInboundRoot() {

        CustomRouter customRouter = new CustomRouter(this);
        Shield shield = new Shield(this);

        Router publicRouter = customRouter.publicResources();
        ChallengeAuthenticator apiGuard = shield.createApiGuard();
        // return publicRouter;

        Router apiRouter = customRouter.createApiRouter();
        apiGuard.setNext(apiRouter);

        publicRouter.attachDefault(apiGuard);

        //Cross origin check
        CustomCorsFilter corsFilter = new CustomCorsFilter(this);
        return corsFilter.createCorsFilter(publicRouter);

    }




}
