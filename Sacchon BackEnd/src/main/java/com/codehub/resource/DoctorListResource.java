package com.codehub.resource;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.representation.DoctorRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;


public interface DoctorListResource {

    @Post("json")
    public DoctorRepresentation add(DoctorRepresentation patientIn)
            throws BadEntityException;
    @Get("json")
    public List<DoctorRepresentation> getDoctors() throws NotFoundException;

}
