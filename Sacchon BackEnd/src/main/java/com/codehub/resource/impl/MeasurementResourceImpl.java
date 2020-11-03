package com.codehub.resource.impl;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.Measurement;
import com.codehub.model.Patient;
import com.codehub.repository.MeasurementRepository;
import com.codehub.repository.PatientRepository;
import com.codehub.representation.MeasurementRepresentation;
import com.codehub.resource.MeasurementResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MeasurementResourceImpl extends ServerResource implements MeasurementResource {

    private MeasurementRepository measurementRepository;
    private PatientRepository patientRepository;
    private EntityManager em;
    private int id;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");

    @Override
    protected void doInit(){
        try {
            em = JpaUtil.getEntityManager();
            measurementRepository = new MeasurementRepository(em);
            patientRepository = new PatientRepository(em);
            id = Integer.parseInt(getAttribute("id"));
        }
        catch(Exception ex){

            throw new ResourceException(ex);
        }

    }

    @Override
    public MeasurementRepresentation getMeasurement() throws NotFoundException {
        Optional<Measurement> measurement = measurementRepository.findById(id);
        setExisting(measurement.isPresent());
        if (!measurement.isPresent()) throw new NotFoundException("Measurement not found");
        //XRHSH LOGGING EDW
        MeasurementRepresentation measurementRepresentation = MeasurementRepresentation.getMeasurementRepresentation(measurement.get());
        return measurementRepresentation;
    }

    @Override
    public void remove() throws NotFoundException {
        measurementRepository.deleteById(id);

    }

    @Override
    public MeasurementRepresentation store(MeasurementRepresentation measurementReprIn) throws NotFoundException, BadEntityException {
        Optional <Measurement> measurementOpt = measurementRepository.findById(id);
        if (!measurementOpt.isPresent()) throw new NotFoundException("Measurement not found");
        Measurement measurement = measurementOpt.get();
        measurement.setGlucoseLevel(measurementReprIn.getGlucoseLevel());
        measurement.setCarbIntake(measurementReprIn.getCarbIntake());
        if (measurementReprIn.getMeds() != null)
            measurement.setMeds(measurementReprIn.getMeds());
        if (measurementReprIn.getDate() != null)
            measurement.setDate( LocalDate.parse(measurementReprIn.getDate(), formatter));

        int givenID = measurementReprIn.getPatientID();
        Optional<Patient> patientOpt = patientRepository.findById(givenID);
        if (!patientOpt.isPresent()) throw new NotFoundException("Doctor not found");
        Patient patient = patientOpt.get();
        measurement.setPatient(patient);
        measurementRepository.save(measurement);
        return MeasurementRepresentation.getMeasurementRepresentation(measurement);
    }

    @Override
    protected  void doRelease(){

        em.close();
    }
}
