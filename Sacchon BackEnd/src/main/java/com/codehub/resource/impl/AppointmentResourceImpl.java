package com.codehub.resource.impl;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.Appointment;
import com.codehub.repository.AppointmentRepository;
import com.codehub.representation.AppointmentRepresentation;
import com.codehub.resource.AppointmentResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class AppointmentResourceImpl extends ServerResource implements AppointmentResource {

    private AppointmentRepository appointmentRepository;
    private EntityManager em;
    private int id;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");

    @Override
    protected void doInit(){
        try {
            em = JpaUtil.getEntityManager();
            appointmentRepository = new AppointmentRepository(em);
            id = Integer.parseInt(getAttribute("id"));
        }
        catch(Exception ex){

            throw new ResourceException(ex);
        }

    }

    @Override
    public AppointmentRepresentation getAppointment() throws NotFoundException {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        setExisting(appointment.isPresent());
        if (!appointment.isPresent()) throw new NotFoundException("Appointment not found");
        //XRHSH LOGGING EDW
        AppointmentRepresentation appointmentRepresentation = AppointmentRepresentation.getAppointmentRepresentation(appointment.get());
        return appointmentRepresentation;
    }

    @Override
    public void remove() throws NotFoundException {

        appointmentRepository.deleteById(id);

    }

    @Override
    public AppointmentRepresentation store(AppointmentRepresentation appointmentReprIn) throws NotFoundException, BadEntityException {
        Optional <Appointment> appointmentOpt = appointmentRepository.findById(id);
        if (!appointmentOpt.isPresent()) throw new NotFoundException("Appointment not found");
        Appointment appointment = appointmentOpt.get();
        if (appointmentReprIn.getDate() != null)
            appointment.setDate( LocalDate.parse(appointmentReprIn.getDate(), formatter));
        if (appointmentReprIn.getConsultation() != null)
            appointment.setConsultation(appointmentReprIn.getConsultation());
        if (appointmentReprIn.getEdit() != null)
            appointment.setEdit(appointmentReprIn.getEdit());

        appointmentRepository.save(appointment);
        return AppointmentRepresentation.getAppointmentRepresentation(appointment);
    }

    @Override
    protected  void doRelease(){

        em.close();
    }
}
