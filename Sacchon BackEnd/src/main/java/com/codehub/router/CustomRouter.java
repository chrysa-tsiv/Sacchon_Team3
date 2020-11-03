package com.codehub.router;

import com.codehub.resource.DoctorAssignNewPatient;
import com.codehub.resource.DoctorNeedConsultResource;
import com.codehub.resource.DoctorSearchForNewPatientResource;
import com.codehub.resource.DoctorSoftRemoveResource;
import com.codehub.resource.impl.*;
import org.restlet.Application;
import org.restlet.routing.Router;

public class CustomRouter {

    private Application application;

    public CustomRouter(Application application){
        this.application =application;
    }

    public Router createApiRouter(){

        Router router = new Router(application.getContext());

        /* Routing table */
        router.attach("/patient/{id}", PatientResourceImpl.class);
        router.attach("/patient", PatientListResourceImpl.class);
        router.attach("/patient/", PatientListResourceImpl.class);
        router.attach("/patient/softRemove/{id}", PatientSoftRemoveResourceImpl.class);

        router.attach("/doctor/{id}", DoctorResourceImpl.class);
        router.attach("/doctor", DoctorListResourceImpl.class);
        router.attach("/doctor/", DoctorListResourceImpl.class);

        router.attach("/measurement/{id}", MeasurementResourceImpl.class);
        router.attach("/measurement", MeasurementListResourceImpl.class);
        router.attach("/measurement/", MeasurementListResourceImpl.class);

        router.attach("/measurement/average", MeasurementAverageResourceImpl.class);
        router.attach("/measurement/average/", MeasurementAverageResourceImpl.class);

        router.attach("/appointment/{id}", AppointmentResourceImpl.class);
        router.attach("/appointment", AppointmentListResourceImpl.class);
        router.attach("/appointment/", AppointmentListResourceImpl.class);

        router.attach("/doctor/softRemove/{id}", DoctorSoftRemoveResourceImpl.class);
        router.attach("/doctor/needConsult/{id}", DoctorNeedConsultResourceImpl.class);
        router.attach("/doctor/assignPatient/{username}", DoctorAssignNewPatientImpl.class);
        router.attach("/doctor/searchNewPatient/", DoctorSearchForNewPatientResourceImpl.class);

        router.attach("/admin/approveDoctor/{username}", AdminApproveDoctorResourceImpl.class);

        return router;

    }

    public Router publicResources() {
        Router router = new Router();
        router.attach("/userSignIn", UserSignInResourceImpl.class);
        return router;
    }


}
