package com.codehub.resource;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.representation.PatientRepresentation;
import org.restlet.resource.Put;

public interface PatientSoftRemoveResource {

    @Put("json")
    public PatientRepresentation store(PatientRepresentation patientRepresentation)
            throws BadEntityException, NotFoundException;
}
