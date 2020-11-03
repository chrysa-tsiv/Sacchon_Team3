package com.codehub.representation;

import com.codehub.common.Gender;
import com.codehub.jpa.JpaUtil;
import com.codehub.model.Doctor;
import com.codehub.model.Patient;
import com.codehub.repository.DoctorRepository;
import com.codehub.repository.MeasurementRepository;
import com.codehub.repository.PatientRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Data
@JsonFormat(pattern = "MM/dd/yyyy")
public class PatientRepresentation {

    private  String name;
    private  String username;
    private String password;
    private   String dob;
    private double height;
    private  double weight;
    private Gender gender;
    private  String history;
    private boolean active;

    private int doctorID;

    private String uri;



    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");
    /* Mapper PatientRepresentation -> Patient */
    static public Patient getPatient(PatientRepresentation patientRepresentation){

        DoctorRepository doctorRepository;
        EntityManager em;

        em = JpaUtil.getEntityManager();
        doctorRepository = new DoctorRepository(em);


        Patient patient = new Patient();

        patient.setName(patientRepresentation.getName());
        patient.setUsername(patientRepresentation.getUsername());
        patient.setPassword(patientRepresentation.getPassword());
        patient.setDob( LocalDate.parse(patientRepresentation.getDob(), formatter));
        patient.setHeight(patientRepresentation.getHeight());
        patient.setWeight(patientRepresentation.getWeight());
        patient.setGender(patientRepresentation.getGender());
        patient.setHistory(patientRepresentation.getHistory());

        int givenID = patientRepresentation.getDoctorID();
        /* The representation does not have any doctor information for the patient */
        if (givenID == 0){
            return patient;
        }
        Optional<Doctor> doctorOpt = doctorRepository.findById(givenID);
        if (!doctorOpt.isPresent()) { return null;} //throw new NotFoundException("Doctor not found");
        Doctor doctor = doctorOpt.get();
        patient.setDoctor(doctor);

        return patient;

    }

    /* Mapper  Patient -> PatientRepresentation */
    static public PatientRepresentation getPatientRepresentation(Patient patient){
        PatientRepresentation patientRepresentation = new PatientRepresentation();
        patientRepresentation.setName(patient.getName());
        patientRepresentation.setUsername(patient.getUsername());
        patientRepresentation.setPassword(patient.getPassword());
        patientRepresentation.setDob(patient.getDob().format(formatter));
        patientRepresentation.setHeight(patient.getHeight());
        patientRepresentation.setWeight(patient.getWeight());
        patientRepresentation.setGender(patient.getGender());
        patientRepresentation.setHistory(patient.getHistory());
        patientRepresentation.setActive(patient.isActive());
        if (patient.getDoctor() != null) {
            patientRepresentation.setDoctorID(patient.getDoctor().getId());
        }
        patientRepresentation.setUri("http://localhost:9000/app/patient/"+patient.getId());

        return patientRepresentation;
    }


}
