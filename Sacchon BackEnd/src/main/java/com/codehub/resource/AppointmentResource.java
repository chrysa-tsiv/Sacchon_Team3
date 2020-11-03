package com.codehub.resource;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.representation.AppointmentRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface AppointmentResource {

    @Get("json")
    public AppointmentRepresentation getAppointment() throws NotFoundException;

    @Delete
    public void remove() throws NotFoundException;

    @Put("json")
    public AppointmentRepresentation store(AppointmentRepresentation appointmentRepresentation)
            throws BadEntityException, NotFoundException;
}
