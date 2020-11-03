package com.codehub.test;

import com.codehub.common.Gender;
import com.codehub.common.MedicalSpecialty;
import com.codehub.exceptions.BadEntityException;
import com.codehub.model.*;
import com.codehub.repository.*;
import com.codehub.representation.AppointmentRepresentation;
import com.codehub.resource.impl.AppointmentListResourceImpl;
import com.codehub.security.CustomRole;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static com.codehub.representation.AppointmentRepresentation.getAppointmentRepresentation;

public class Test {

    EntityManager em;
    DoctorRepository dpr;
    PatientRepository str;
    MeasurementRepository msm;
    AppointmentRepository apr;
    ChiefAdministratorRepository chiefrep;

    public Test(EntityManager em, DoctorRepository dpr, PatientRepository str, MeasurementRepository msm, AppointmentRepository apr, ChiefAdministratorRepository chiefrep) {
        this.em = em;
        this.dpr = dpr;
        this.str = str;
        this.msm = msm;
        this.apr = apr;
        this.chiefrep = chiefrep;

    }

    public void test1() {

        ChiefAdministrator chiefAdministrator = new ChiefAdministrator("admin","passw0rd", CustomRole.ROLE_ADMIN);
        this.chiefrep.save(chiefAdministrator);

        Doctor doctor1 = new Doctor("George", "george21", "george21@", MedicalSpecialty.CARDIOLOGY, "George description");
        this.dpr.save(doctor1);
        Doctor doctor2 = new Doctor("Dimitrios", "dimitrios45", "dimitrios4567&", MedicalSpecialty.NEUROLOGY, "Dimitrios description");
        this.dpr.save(doctor2);
        Doctor doctor3 = new Doctor("Maria", "maria_dimitriou", "maria_dimit456&", MedicalSpecialty.NEUROLOGY, "Maria description");
        this.dpr.save(doctor3);

        Patient patient1 = new Patient("Dimitris","dimi28","dimt2345@", Gender.MALE, LocalDate.parse("1987-01-10"),1.89,87,"Dimitrios history");

        Patient patient2 = new Patient("Panagiotis","pan43","pant2345@",Gender.MALE,LocalDate.parse("1956-01-17"),1.67,68,"Panagiotis history");
        Patient patient3 = new Patient("Eleni","elen7897","elt1245@",Gender.FEMALE,LocalDate.parse("1995-01-10"),1.78,77,"Eleni history");
        Patient patient4 = new Patient("George","george_kostop","g_kost1245@",Gender.FEMALE,LocalDate.parse("1995-01-10"),1.78,77,"George history");

        patient1.setDoctor(doctor1);
        patient2.setDoctor(doctor1);
        patient3.setDoctor(doctor2);
        patient4.setDoctor(doctor3);


        doctor1.setNumOfPatients(2);
        doctor2.setNumOfPatients(1);
        doctor3.setNumOfPatients(1);


        str.save(patient1);
        str.save(patient2);
        str.save(patient3);
        str.save(patient4);


//////////////////////////////PATIENT_ID=1//////////////////////////////////////////////////////
        for (int i=0;i<78;i++){
            LocalDate test = LocalDate.now();
            test = test.minusDays(70 - i);
            if (i<30){
                Measurement meas5 = new Measurement(test, (int) (Math.random() * 200 + 10), (int) (Math.random() * 200 + 50), "test med" + i);
               meas5.setPatient(patient1);
                msm.save(meas5);
            }
            if (i==34) {
                String rt =  em.createQuery("SELECT needConsulting FROM Patient WHERE id = '" + patient1.getId()+"'").getSingleResult().toString();
                System.out.println(rt);
                em.getTransaction().begin();
                em.createQuery("UPDATE Patient SET needConsulting='false' where id = " + patient1.getId()).executeUpdate();
                em.createQuery("UPDATE Patient SET first_entry='" + test + "' where id = " + patient1.getId()).executeUpdate();
                em.getTransaction().commit();
                Appointment appointment1 = new Appointment(test, "We are in a good way", "more excercise");
                appointment1.setDoctor(doctor1);
                appointment1.setPatient(patient1);
                apr.save(appointment1);
            }
            if (i>34 && i<67){
               Measurement meas5 = new Measurement(test, (int) (Math.random() * 100 + 100), (int) (Math.random() * 100 + 300), "test med" + i);
               meas5.setPatient(patient1);
               msm.save(meas5);
            }
            if (i==68)
            {
                em.getTransaction().begin();
                em.createQuery("UPDATE Patient SET needConsulting='false' where id = " + patient1.getId()).executeUpdate();
                em.createQuery("UPDATE Patient SET first_entry='" + test + "' where id = " + patient1.getId()).executeUpdate();
                em.getTransaction().commit();
                Appointment appointment1 = new Appointment(test, "Please reduce carb consumption", "n/a");
                appointment1.setDoctor(doctor1);
                appointment1.setPatient(patient1);
                apr.save(appointment1);

            }
            if (i>68){
                Measurement meas5 = new Measurement(test, (int) (Math.random() * 100 + 100), (int) (Math.random() * 100 + 300), "test med" + i);
                meas5.setPatient(patient1);
                msm.save(meas5);
            }
        }

////////////////////////////////////////////////////PATIENT_ID=2//////////////////////////////////////////////////////
        for (int i=0;i<31;i++){
           LocalDate test = LocalDate.now();
           test = test.minusDays(32-i);
           Measurement meas5 = new Measurement(test,(int)(Math.random()*100+100),(int)(Math.random()*100+500),"test med"+i);
           meas5.setPatient(patient2);
           msm.save(meas5);
        }

////////////////////////////////////////PATIENT_ID=3/////////////////////////////////////////////////////////////////////////
        for (int i=0;i<50;i++){
            LocalDate test = LocalDate.now();
            test = test.minusDays(60-i);
            if (i==32){

                em.getTransaction().begin();
                em.createQuery("UPDATE Patient SET needConsulting='false' where id = " + patient3.getId()).executeUpdate();
                em.createQuery("UPDATE Patient SET first_entry='" + test + "' where id = " + patient3.getId()).executeUpdate();
                em.getTransaction().commit();
                Appointment appointment1 = new Appointment(test, "Very low glucose levels decrease insuline", "n/a");
                appointment1.setDoctor(doctor2);
                appointment1.setPatient(patient3);
                apr.save(appointment1);

            }else {

                Measurement meas5 = new Measurement(test, (int) (Math.random() * 100 + 100), (int) (Math.random() * 100 + 500), "test med" + i);
                meas5.setPatient(patient3);
                msm.save(meas5);
            }

        }
///////////////////////////////////////////////////PATIENT_ID=4/////////////////////////////////////////////////////////////////////////
        for (int i=0;i<20;i++){
            LocalDate test = LocalDate.now();
            test = test.minusDays(32-i);
            Measurement meas5 = new Measurement(test,(int)(Math.random()*100+100),(int)(Math.random()*100+500),"test med"+i);
            meas5.setPatient(patient4);
            msm.save(meas5);
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        List<Patient> queryPatients =
                em.createQuery("SELECT p FROM Patient p WHERE doctor.id = :id", Patient.class)
                        .setParameter("id", 2)
                        .getResultList();

        queryPatients
                .stream()
                .forEach(System.out::println);

        List<Measurement> queryMeasurements2 =
                em.createQuery("SELECT m FROM Measurement m WHERE patient.id= :id", Measurement.class)
                        .setParameter("id", 1)
                        .getResultList();
        queryMeasurements2
                .stream()
                .forEach(System.out::println);

    //    Appointment appointment1 = new Appointment(LocalDate.parse("2020-10-18"), "We are in a good way", "I modify this");
     //   Appointment appointment2 = new Appointment(LocalDate.parse("2015-11-18"), "Stop the drugs", "As soon as possible");
    //    appointment1.setDoctor(doctor);
    //    appointment2.setDoctor(doctor2);
    //    appointment1.setPatient(patient);
     //   appointment2.setPatient(patient2);
    //    apr.save(appointment1);
    //    apr.save(appointment2);

    }

}




// OLD TEST
/*package com.codehub.test;

import com.codehub.common.Gender;
import com.codehub.common.MedicalSpecialty;
import com.codehub.model.*;
import com.codehub.repository.*;
import com.codehub.security.CustomRole;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class Test {

    EntityManager em;
    DoctorRepository dpr;
    PatientRepository str;
    MeasurementRepository msm;
    AppointmentRepository apr;
    ChiefAdministratorRepository chiefrep;

    public Test(EntityManager em, DoctorRepository dpr, PatientRepository str, MeasurementRepository msm, AppointmentRepository apr, ChiefAdministratorRepository chiefrep) {
        this.em = em;
        this.dpr = dpr;
        this.str = str;
        this.msm = msm;
        this.apr = apr;
        this.chiefrep = chiefrep;

    }

    public void test1() {

        ChiefAdministrator chiefAdministrator = new ChiefAdministrator("admin","passw0rd", CustomRole.ROLE_ADMIN);
        this.chiefrep.save(chiefAdministrator);

        Doctor doctor = new Doctor("Dr Test Smth", "DrSmthSmth!", "passwordtest1", MedicalSpecialty.CARDIOLOGY, "test description");
        this.dpr.save(doctor);
        Doctor doctor2 = new Doctor("THOMAS", "thomas", "passwordtest2", MedicalSpecialty.NEUROLOGY, "description");
        this.dpr.save(doctor2);

        Patient patient = new Patient("Dimitris","dimi28","dimt2345@", Gender.MALE, LocalDate.parse("1987-01-10"),1.89,87,"test history");

        Patient patient2 = new Patient("Panagiotis","pan43","pant2345@",Gender.MALE,LocalDate.parse("1934-01-17"),1.67,68,"test history Panagiotis");
        Patient patient3 = new Patient("Eleni","el4567","elt1245@",Gender.FEMALE,LocalDate.parse("1995-01-10"),1.78,77,"test history ELEN");

        patient.setDoctor(doctor);
        patient2.setDoctor(doctor);
        patient3.setDoctor(doctor2);

        str.save(patient);
        str.save(patient2);
        str.save(patient3);

        //DELETE THIS PRINT FROM TEST LATER , OR MAKE IT WITH LOGGER
        patient = str.findById(1).get();
        System.out.println(patient.toString());

        //    Measurement meas = new Measurement(LocalDate.parse("2020-10-21"),200,200,"test med1");
        //    Measurement meas1 = new Measurement(LocalDate.parse("2020-10-20"),120,110,"test med2");
        //    Measurement meas2 = new Measurement(LocalDate.parse("2020-10-19"),123,111,"test med3");
        Measurement meas3 = new Measurement(LocalDate.parse("2020-10-18"),123,111,"test med4");
        //     meas.setPatient(patient);
        //     meas2.setPatient(patient2);
        meas3.setPatient(patient3);
        //     meas1.setPatient(patient);
        //   msm.save(meas);
        //    msm.save(meas);
        //     msm.save(meas1);
        //     msm.save(meas2);
        msm.save(meas3);

        for (int i=0;i<30;i++){
            LocalDate test = LocalDate.now();
            test = test.minusDays(30 - i);
            if (i<15){
//test__
                Measurement meas5 = new Measurement(test, (int) (Math.random() * 100 + 100), (int) (Math.random() * 100 + 500), "test med" + i);
                meas5.setPatient(patient);
                msm.save(meas5);
            }
            if (i==17) {
                String rt =  em.createQuery("SELECT available FROM Patient WHERE id = '" + patient.getId()+"'").getSingleResult().toString();
                System.out.println(rt);
                em.getTransaction().begin();
                em.createQuery("UPDATE Patient SET available='false' where id = " + patient.getId()).executeUpdate();
                em.createQuery("UPDATE Patient SET first_entry='" + test + "' where id = " + patient.getId()).executeUpdate();
                em.getTransaction().commit();
                //       em.clear();
            }
            if (i>20){

                Measurement meas5 = new Measurement(test, (int) (Math.random() * 100 + 100), (int) (Math.random() * 100 + 500), "test med" + i);
                meas5.setPatient(patient);
                msm.save(meas5);
            }

        }
        for (int i=0;i<5;i++){
            LocalDate test = LocalDate.now();
            test = test.minusDays(32-i);
            Measurement meas5 = new Measurement(test,(int)(Math.random()*100+100),(int)(Math.random()*100+500),"test med"+i);
            meas5.setPatient(patient2);
            msm.save(meas5);
        }

        List<Patient> queryPatients =
                em.createQuery("SELECT p FROM Patient p WHERE doctor.id = :id", Patient.class)
                        .setParameter("id", 2)
                        .getResultList();

        queryPatients
                .stream()
                .forEach(System.out::println);

        List<Measurement> queryMeasurements2 =
                em.createQuery("SELECT m FROM Measurement m WHERE patient.id= :id", Measurement.class)
                        .setParameter("id", 1)
                        .getResultList();
        queryMeasurements2
                .stream()
                .forEach(System.out::println);

        Appointment appointment1 = new Appointment(LocalDate.parse("2020-10-18"), "We are in a good way", "I modify this");
        Appointment appointment2 = new Appointment(LocalDate.parse("2015-11-18"), "Stop the drugs", "As soon as possible");
        appointment1.setDoctor(doctor);
        appointment2.setDoctor(doctor2);
        appointment1.setPatient(patient);
        appointment2.setPatient(patient2);
        apr.save(appointment1);
        apr.save(appointment2);

    }

} */
