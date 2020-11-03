package com.codehub.resource.impl;

import com.codehub.exceptions.BadEntityException;
import com.codehub.exceptions.NotFoundException;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.Appointment;
import com.codehub.model.Doctor;
import com.codehub.model.Patient;
import com.codehub.repository.AppointmentRepository;
import com.codehub.repository.DoctorRepository;
import com.codehub.repository.PatientRepository;
import com.codehub.representation.AppointmentRepresentation;
import com.codehub.resource.AppointmentListResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class AppointmentListResourceImpl extends ServerResource implements AppointmentListResource {

    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private EntityManager em;


    @Override
    protected void doInit(){
        try {
            em = JpaUtil.getEntityManager();
            appointmentRepository = new AppointmentRepository(em);
            doctorRepository = new DoctorRepository(em);
            patientRepository = new PatientRepository(em);
        }
        catch(Exception ex){

            throw new ResourceException(ex);
        }

    }

    @Override
    public AppointmentRepresentation add(AppointmentRepresentation appointmentIn) throws BadEntityException {

        Appointment appointment = AppointmentRepresentation.getAppointment(appointmentIn);


        List<Patient> patientReturn = patientRepository.findByUsername(appointmentIn.getPatientUsername());
        if (patientReturn.size() == 0) { /* Doctor gave wrong username for patient to add */
            return null;
        }
        Patient patient = patientReturn.get(0);

        /* Update doctor's fields */
        Optional<Doctor> doctorOpt = doctorRepository.findById(appointmentIn.getDoctorID());
        if (!doctorOpt.isPresent()) {
            return null; //TO JSON dinei id giatrou pou den yparxei
        } //throw new NotFoundException("Doctor not found");
        Doctor doctor = doctorOpt.get();

        //AYTA THA MPOUNE STO ASSIGN
        /* It's patient's first time for consultation; */
        //if (patient.isAvailable()){ /* First consultation */
        //    patient.setDoctor(doctor);
        //    patient.setAvailable(false);
        //    doctor.setNumOfPatients(doctor.getNumOfPatients() + 1);
        //}
        patient.setNeedConsulting(false);
        patient.setFirst_entry(LocalDate.now());

        patientRepository.save(patient);
        doctorRepository.save(doctor);
        appointmentRepository.save(appointment);
        return AppointmentRepresentation.getAppointmentRepresentation(appointment);

    }

 //   @Override
//    public List<AppointmentRepresentation> getAppointments() throws NotFoundException {
//        return null;
 //   }

    @Override
    public List<AppointmentRepresentation> getAppointments() throws NotFoundException {
        //List<String> roles = new ArrayList<>();
        //roles.add(CustomRole.ROLE_PATIENT.getRoleName());
        //roles.add(CustomRole.ROLE_DOCTOR.getRoleName());
        //ResourceUtils.checkRole(this, roles);
        try {
            int doctorID = parseInt(getQueryValue("doctorID"));
            int patientID = parseInt(getQueryValue("patientID"));
            List<Appointment> appointments = appointmentRepository.findByPatientDoctorID(patientID, doctorID);
            List<AppointmentRepresentation> appointmentRepresentationList = new ArrayList<>();
            appointments.forEach(appointment -> appointmentRepresentationList.add(AppointmentRepresentation.getAppointmentRepresentation(appointment)));
            return appointmentRepresentationList;
        }catch (Exception e) {

        }
        try {
            int doctorID = parseInt(getQueryValue("doctorID"));
            List<Appointment> appointments = appointmentRepository.findByDoctorID(doctorID);
            List<AppointmentRepresentation> appointmentRepresentationList = new ArrayList<>();
            appointments.forEach(appointment -> appointmentRepresentationList.add(AppointmentRepresentation.getAppointmentRepresentation(appointment)));
            return appointmentRepresentationList;
        }catch (Exception e) {
        }
        try {
            int patientID = parseInt(getQueryValue("patientID"));
            List<Appointment> appointments = appointmentRepository.findByPatientID(patientID);
            List<AppointmentRepresentation> appointmentRepresentationList = new ArrayList<>();
            appointments.forEach(appointment -> appointmentRepresentationList.add(AppointmentRepresentation.getAppointmentRepresentation(appointment)));
            return appointmentRepresentationList;
        } catch (Exception e) {
        }
        List<AppointmentRepresentation> appointmentRepresentationList = new ArrayList<>();
        List<Appointment> appointments = appointmentRepository.findAll();
        appointments.forEach(appointment -> appointmentRepresentationList.add(AppointmentRepresentation.getAppointmentRepresentation(appointment)));
        return appointmentRepresentationList;

    }






    protected void doRelease(){
        em.close();
    }


}
