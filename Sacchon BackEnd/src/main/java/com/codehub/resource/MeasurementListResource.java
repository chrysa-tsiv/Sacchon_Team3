package com.codehub.resource;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.representation.MeasurementRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

public interface MeasurementListResource {


    @Post("json")
    public MeasurementRepresentation add(MeasurementRepresentation measurementIn)
            throws BadEntityException;

    @Get("json")
    public List<MeasurementRepresentation> getMeasurements() throws NotFoundException;
}
