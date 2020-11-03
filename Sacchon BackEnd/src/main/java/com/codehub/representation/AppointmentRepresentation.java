package com.codehub.representation;

import com.codehub.jpa.JpaUtil;
import com.codehub.model.Appointment;
import com.codehub.model.Doctor;
import com.codehub.model.Patient;
import com.codehub.repository.DoctorRepository;
import com.codehub.repository.MeasurementRepository;
import com.codehub.repository.PatientRepository;
import lombok.Data;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static com.codehub.representation.PatientRepresentation.formatter;

@Data
public class AppointmentRepresentation {

    private String date;
    private String consultation;
    private String edit;
    private int doctorID;
    private String patientUsername;

    private String uri;

    /* Mapper AppointmentRepresentation -> Appointment */
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");

    public static Appointment getAppointment(AppointmentRepresentation appointmentRepresentation){


        PatientRepository patientRepository;
        DoctorRepository doctorRepository;
        EntityManager em;

        em = JpaUtil.getEntityManager();
        patientRepository = new PatientRepository(em);
        doctorRepository = new DoctorRepository(em);

        //TO SWSTO TMIMA ME TO EXCEPTION
        // try {
        //     em = JpaUtil.getEntityManager();
        //     patientRepository = new PatientRepository(em);
        //     measurementRepository = new MeasurementRepository(em);
        // }
        // catch(Exception ex){
//
        //throw new ResourceException(ex);
        //      }



        Appointment appointment = new Appointment();
        appointment.setDate( LocalDate.parse(appointmentRepresentation.getDate(), formatter));
        if(appointmentRepresentation.getConsultation() != null){
            appointment.setConsultation(appointmentRepresentation.getConsultation());
        }
        if(appointmentRepresentation.getEdit() != null) {
            appointment.setEdit(appointmentRepresentation.getEdit());
        }
        String patientUsernameInput = appointmentRepresentation.getPatientUsername();
        List<Patient> patient = patientRepository.findByUsername(patientUsernameInput);
        if (patient.size() == 0) { /* Doctor gave wrong username for patient */
            return null;
        }
        appointment.setPatient(patient.get(0));


        Optional<Doctor> doctorOpt = doctorRepository.findById(appointmentRepresentation.getDoctorID());
        if (!doctorOpt.isPresent()) { return null;} //throw new NotFoundException("Doctor not found");
        Doctor doctor = doctorOpt.get();

        appointment.setDoctor(doctor);



        return appointment;
    }

    /* Mapper Appointment -> AppointmentRepresentation */
    public static AppointmentRepresentation getAppointmentRepresentation(Appointment appointment){
        AppointmentRepresentation appointmentRepresentation = new AppointmentRepresentation();
        appointmentRepresentation.setDate(appointment.getDate().format(formatter));
        appointmentRepresentation.setConsultation(appointment.getConsultation());
        appointmentRepresentation.setEdit(appointment.getEdit());
        appointmentRepresentation.setDoctorID(appointment.getDoctor().getId());
        appointmentRepresentation.setPatientUsername(appointment.getPatient().getUsername());
        appointmentRepresentation.setUri("http://localhost:9000/app/appointment/"+ appointment.getId());

        return appointmentRepresentation;
    }


}