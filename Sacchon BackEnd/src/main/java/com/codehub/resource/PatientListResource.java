package com.codehub.resource;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.representation.PatientRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

public interface PatientListResource {


    @Post("json")
    public PatientRepresentation add(PatientRepresentation patientIn)
            throws BadEntityException;
    @Get("json")
    public List<PatientRepresentation> getPatients() throws NotFoundException;
}
