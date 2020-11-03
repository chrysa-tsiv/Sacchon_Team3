package com.codehub.resource.impl;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.Measurement;
import com.codehub.model.Patient;
import com.codehub.repository.MeasurementRepository;
import com.codehub.representation.MeasurementRepresentation;
import com.codehub.representation.PatientRepresentation;
import com.codehub.resource.MeasurementListResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MeasurementListResourceImpl extends ServerResource implements MeasurementListResource {

    private MeasurementRepository measurementRepository;
    private EntityManager em;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");


    @Override
    protected void doInit(){
        try {
            em = JpaUtil.getEntityManager();
            measurementRepository = new MeasurementRepository(em);
        }
        catch(Exception ex){

            throw new ResourceException(ex);
        }

    }


    @Override
    public MeasurementRepresentation add(MeasurementRepresentation measurementIn) throws BadEntityException {
        /* Validation check */
        if (measurementIn == null) {
            throw new BadEntityException("Measurement null representation error");
        }

        Measurement measurement = MeasurementRepresentation.getMeasurement(measurementIn);
        measurementRepository.save(measurement);
        return MeasurementRepresentation.getMeasurementRepresentation(measurement);
    }

 //   @Override
 //   public List<MeasurementRepresentation> getMeasurements() throws NotFoundException {
 //       return null;
 //   }

    @Override
    public List<MeasurementRepresentation> getMeasurements() throws NotFoundException {
        //List<String> roles = new ArrayList<>();
        //roles.add(CustomRole.ROLE_PATIENT.getRoleName());
        //roles.add(CustomRole.ROLE_DOCTOR.getRoleName());
        //ResourceUtils.checkRole(this, roles);
        try {
            int patientID = parseInt(getQueryValue("patientID"));
            String fromDate = getQueryValue("fromDate");
            String toDate = getQueryValue("toDate");
            if (patientID == 0) throw new Exception();
            if (fromDate == null) throw new Exception();
            if (toDate == null) throw new Exception();
            List<Measurement> measurements = measurementRepository.findByPatientIDandDate(patientID, LocalDate.parse(fromDate, formatter), LocalDate.parse(toDate, formatter));
            List<MeasurementRepresentation> measurementRepresentationList = new ArrayList<>();
            measurements.forEach(measurement -> measurementRepresentationList.add(MeasurementRepresentation.getMeasurementRepresentation(measurement)));
            return measurementRepresentationList;

        } catch (Exception e) {
        }
        try {
            int patientID = parseInt(getQueryValue("patientID"));
            String fromDate = getQueryValue("fromDate");
            if (patientID == 0) throw new Exception();
            if (fromDate == null) throw new Exception();
            List<Measurement> measurements = measurementRepository.findByPatientIDandDate(patientID, LocalDate.parse(fromDate, formatter));
            List<MeasurementRepresentation> measurementRepresentationList = new ArrayList<>();
            measurements.forEach(measurement -> measurementRepresentationList.add(MeasurementRepresentation.getMeasurementRepresentation(measurement)));
            return measurementRepresentationList;

        } catch (Exception e) {
        }
        try {
            int patientID = parseInt(getQueryValue("patientID"));
            if (patientID == 0) throw new Exception();
            List<Measurement> measurements = measurementRepository.findByPatientID(patientID);
            List<MeasurementRepresentation> measurementRepresentationList = new ArrayList<>();
            measurements.forEach(measurement -> measurementRepresentationList.add(MeasurementRepresentation.getMeasurementRepresentation(measurement)));
            return measurementRepresentationList;
        } catch (Exception e) {
        }
        try {
            String patientUsername = getQueryValue("patientUsername");
            String fromDate = getQueryValue("fromDate");
            String toDate = getQueryValue("toDate");
            if (patientUsername == "") throw new Exception();
            List<Measurement> measurements = measurementRepository.findByPatientUsernameandDate(patientUsername, LocalDate.parse(fromDate, formatter),LocalDate.parse(toDate, formatter));
            List<MeasurementRepresentation> measurementRepresentationList = new ArrayList<>();
            measurements.forEach(measurement -> measurementRepresentationList.add(MeasurementRepresentation.getMeasurementRepresentation(measurement)));
            return measurementRepresentationList;

        } catch (Exception e) {
        }
        try {
            String patientUsername = getQueryValue("patientUsername");
            String fromDate = getQueryValue("fromDate");
            if (fromDate == null) throw new Exception();
            List<Measurement> measurements = measurementRepository.findByPatientUsernameandDate(patientUsername, LocalDate.parse(fromDate, formatter));
            List<MeasurementRepresentation> measurementRepresentationList = new ArrayList<>();
            measurements.forEach(measurement -> measurementRepresentationList.add(MeasurementRepresentation.getMeasurementRepresentation(measurement)));
            return measurementRepresentationList;

        } catch (Exception e) {
        }
        try {
            String patientUsername = getQueryValue("patientUsername");
            if (patientUsername == null) throw new Exception();
            List<Measurement> measurements = measurementRepository.findByPatientUsername(patientUsername);
            List<MeasurementRepresentation> measurementRepresentationList = new ArrayList<>();
            measurements.forEach(measurement -> measurementRepresentationList.add(MeasurementRepresentation.getMeasurementRepresentation(measurement)));
            return measurementRepresentationList;
        } catch (Exception e) {
        }
        List<Measurement> measurements = measurementRepository.findAll();
        List<MeasurementRepresentation> measurementRepresentationList = new ArrayList<>();
        measurements.forEach(measurement -> measurementRepresentationList.add(MeasurementRepresentation.getMeasurementRepresentation(measurement)));
        return measurementRepresentationList;

    }



    protected void doRelease(){
        em.close();
    }

}
