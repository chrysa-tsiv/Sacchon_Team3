package com.codehub.resource;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.representation.PatientRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface PatientResource {

    @Get("json")
    public PatientRepresentation getPatient() throws NotFoundException;

    @Delete
    public void remove() throws NotFoundException;

    @Put("json")
    public PatientRepresentation store(PatientRepresentation patientRepresentation)
        throws BadEntityException, NotFoundException;

}
