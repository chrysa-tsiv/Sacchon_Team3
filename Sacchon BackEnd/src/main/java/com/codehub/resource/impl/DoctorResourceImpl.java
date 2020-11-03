package com.codehub.resource.impl;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.Doctor;
import com.codehub.repository.DoctorRepository;
import com.codehub.representation.DoctorRepresentation;
import com.codehub.resource.DoctorResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.Optional;

public class DoctorResourceImpl extends ServerResource implements DoctorResource {

    private DoctorRepository doctorRepository;
    private EntityManager em;
    private int id;

    @Override
    protected void doInit(){
        try {
            em = JpaUtil.getEntityManager();
            doctorRepository = new DoctorRepository(em);
            id = Integer.parseInt(getAttribute("id"));
        }
        catch(Exception ex){

            throw new ResourceException(ex);
        }

    }


    @Override
    public DoctorRepresentation getDoctor() throws NotFoundException {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        setExisting(doctor.isPresent());
        if (!doctor.isPresent()) throw new NotFoundException("Doctor is not found");
        //XRHSH LOGGING EDW
        DoctorRepresentation doctorRepresentation = DoctorRepresentation.getDoctorRepresentation(doctor.get());
        return doctorRepresentation;
    }

    @Override
    public void remove() throws NotFoundException {
        doctorRepository.deleteById(id);
    }

    @Override
    public DoctorRepresentation store(DoctorRepresentation doctorReprIn) throws BadEntityException, NotFoundException {
        Optional <Doctor> doctorOpt = doctorRepository.findById(id);
        if (!doctorOpt.isPresent()) throw new NotFoundException("Doctor not found");
        Doctor doctor = doctorOpt.get();
        if (doctorReprIn.getName() != null)
            doctor.setName(doctorReprIn.getName());
        if (doctorReprIn.getUsername() != null)
            doctor.setUsername(doctorReprIn.getUsername());
        if (doctorReprIn.getPassword() != null)
            doctor.setPassword(doctorReprIn.getPassword());
        if (doctorReprIn.getSpecialty() != null)
            doctor.setSpecialty(doctorReprIn.getSpecialty());
        doctor.setNumOfPatients(doctorReprIn.getNumOfPatients());
        doctor.setActive(doctorReprIn.isActive());
        if (doctorReprIn.getDescription() != null)
            doctor.setDescription(doctorReprIn.getDescription());

        doctorRepository.save(doctor);
        return DoctorRepresentation.getDoctorRepresentation(doctor);

    }

    protected void doRelease(){
        em.close();
    }
}
