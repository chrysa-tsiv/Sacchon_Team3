package com.codehub.resource;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.representation.MeasurementRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface MeasurementResource {

    @Get("json")
    public MeasurementRepresentation getMeasurement() throws NotFoundException;

    @Delete
    public void remove() throws NotFoundException;

    @Put("json")
    public MeasurementRepresentation store(MeasurementRepresentation measurementRepresentation)
            throws BadEntityException, NotFoundException;
}
