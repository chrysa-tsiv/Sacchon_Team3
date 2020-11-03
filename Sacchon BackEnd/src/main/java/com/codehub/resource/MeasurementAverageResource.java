package com.codehub.resource;

import com.codehub.exceptions.NotFoundException;
import com.codehub.representation.MeasurementAverageRepresentation;
import org.restlet.resource.Get;

public interface MeasurementAverageResource {

    @Get("json")
    public MeasurementAverageRepresentation getAverageMeasurement() throws NotFoundException;



    //  @Put("json")
    //  public MeasurementRepresentation store(MeasurementRepresentation measurementRepresentation)
    //          throws BadEntityException, NotFoundException;

}
