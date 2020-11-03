package com.codehub.resource;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.representation.AppointmentRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

public interface AppointmentListResource {

    @Post("json")
    public AppointmentRepresentation add(AppointmentRepresentation appointmentIn)
            throws BadEntityException;
    @Get("json")
    public List<AppointmentRepresentation> getAppointments() throws NotFoundException;
}
