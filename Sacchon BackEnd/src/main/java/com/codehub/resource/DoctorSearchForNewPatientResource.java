package com.codehub.resource;

import com.codehub.exceptions.NotFoundException;
import com.codehub.representation.PatientRepresentation;
import org.restlet.resource.Get;

import java.util.List;

public interface DoctorSearchForNewPatientResource {
    @Get("json")
    public List<PatientRepresentation> getNewPatients() throws NotFoundException;
}
