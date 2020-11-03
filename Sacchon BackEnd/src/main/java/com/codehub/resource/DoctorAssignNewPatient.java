package com.codehub.resource;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.representation.DoctorRepresentation;
import org.restlet.resource.Put;

public interface DoctorAssignNewPatient {

    @Put("json")
    public DoctorRepresentation store(DoctorRepresentation doctorRepresentation)
            throws BadEntityException, NotFoundException;


}
