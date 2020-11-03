package com.codehub.representation;

import com.codehub.exceptions.NotFoundException;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.Doctor;
import com.codehub.model.Measurement;
import com.codehub.model.Patient;
import com.codehub.repository.DoctorRepository;
import com.codehub.repository.MeasurementRepository;
import com.codehub.repository.PatientRepository;
import lombok.Data;
import org.restlet.resource.ResourceException;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Data
public class MeasurementRepresentation {

    private double glucoseLevel;
    private double carbIntake;
    private String meds;
    private String date;
    private String uri;
    private int patientID;
    private int measurementID;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");



    /* Mapper MeasurementRepresentation -> Measurement */
    public static Measurement getMeasurement(MeasurementRepresentation measurementRepresentation) {

        PatientRepository patientRepository;
        MeasurementRepository measurementRepository;
        EntityManager em;

        em = JpaUtil.getEntityManager();
        patientRepository = new PatientRepository(em);
        measurementRepository = new MeasurementRepository(em);

        //TO SWSTO TMIMA ME TO EXCEPTION
       // try {
       //     em = JpaUtil.getEntityManager();
       //     patientRepository = new PatientRepository(em);
       //     measurementRepository = new MeasurementRepository(em);
       // }
       // catch(Exception ex){
//
        //throw new ResourceException(ex);
  //      }


        Measurement measurement = new Measurement();
        measurement.setGlucoseLevel(measurementRepresentation.getGlucoseLevel());
        measurement.setCarbIntake(measurementRepresentation.getCarbIntake());
        measurement.setMeds(measurementRepresentation.getMeds());
        measurement.setDate( LocalDate.parse(measurementRepresentation.getDate(), formatter));

        int givenID = measurementRepresentation.getPatientID();
        Optional<Patient> patientOpt = patientRepository.findById(givenID);
        if (!patientOpt.isPresent()) { return null;} //throw new NotFoundException("Doctor not found");
        Patient patient = patientOpt.get();
        measurement.setPatient(patient);


        return measurement;
    }

    /* Mapper Measurement -> MeasurementRepresentation */
    public static MeasurementRepresentation getMeasurementRepresentation(Measurement measurement){
        MeasurementRepresentation measurementRepresentation = new MeasurementRepresentation();
        measurementRepresentation.setGlucoseLevel(measurement.getGlucoseLevel());
        measurementRepresentation.setCarbIntake(measurement.getCarbIntake());
        measurementRepresentation.setMeds(measurement.getMeds());
        measurementRepresentation.setDate(measurement.getDate().format(formatter));
        measurementRepresentation.setPatientID(measurement.getPatient().getId());
        measurementRepresentation.setMeasurementID(measurement.getId());
        measurementRepresentation.setUri("http://localhost:9000/app/measurements/"+measurement.getId());


        return measurementRepresentation;
    }
}
