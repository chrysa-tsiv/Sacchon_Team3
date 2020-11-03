package com.codehub.resource.impl;

import com.codehub.exceptions.NotFoundException;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.Measurement;
import com.codehub.repository.MeasurementRepository;
import com.codehub.representation.MeasurementAverageRepresentation;
import com.codehub.representation.MeasurementRepresentation;
import com.codehub.resource.MeasurementAverageResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MeasurementAverageResourceImpl extends ServerResource implements MeasurementAverageResource {

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

    //  @Override
    //   public List<MeasurementRepresentation> getMeasurementsAverage() throws NotFoundException {
    //       return null;
    //   }


    @Override
    public MeasurementAverageRepresentation getAverageMeasurement() throws NotFoundException {

        try {
            int patientID = parseInt(getQueryValue("patientID"));
            String fromDate = getQueryValue("fromDate");
            String toDate = getQueryValue("toDate");
            if (patientID== 0) throw new Exception();
            List<Measurement> measurements = measurementRepository.findByPatientIDandDate(patientID, LocalDate.parse(fromDate, formatter),LocalDate.parse(toDate, formatter));
            List<MeasurementRepresentation> measurementRepresentationList = new ArrayList<>();
            measurements.forEach(measurement -> measurementRepresentationList.add(MeasurementRepresentation.getMeasurementRepresentation(measurement)));
            int n=0;
            double averageGlucose = 0;
            double averageCarbs = 0;
            for(MeasurementRepresentation measurementRepresentation : measurementRepresentationList){
                averageGlucose = averageGlucose + measurementRepresentation.getGlucoseLevel();
                averageCarbs = averageCarbs + measurementRepresentation.getCarbIntake();
                n++;
            }

            MeasurementAverageRepresentation measurementAverageRepresentation = new MeasurementAverageRepresentation();
            measurementAverageRepresentation.setGlucoseLevel(((double) averageGlucose) / n);
            measurementAverageRepresentation.setCarbIntake(((double)averageCarbs) / n);
            return measurementAverageRepresentation;


        } catch (Exception e) {
            return null;
        }

    }

    protected void doRelease(){
        em.close();
    }

}
