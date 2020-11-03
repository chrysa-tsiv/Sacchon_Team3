package com.codehub.representation;

import com.codehub.common.MedicalSpecialty;
import com.codehub.model.Doctor;
import lombok.Data;

@Data
public class DoctorRepresentation {

    private String name;
    private String username;
    private String password;
    private MedicalSpecialty specialty;
    private int numOfPatients;
    private String description;
    private boolean active;

    private String uri;


    /* Mapper DoctorRepresentation -> Doctor */
    static public Doctor getDoctor(DoctorRepresentation doctorRepresentation){
        Doctor doctor = new Doctor();
        doctor.setName(doctorRepresentation.getName());
        doctor.setUsername(doctorRepresentation.getUsername());
        doctor.setPassword(doctorRepresentation.getPassword());
        doctor.setSpecialty(doctorRepresentation.getSpecialty());
        doctor.setNumOfPatients(doctorRepresentation.getNumOfPatients());
        doctor.setDescription(doctorRepresentation.getDescription());
        /* Active variable doesn't count */

        return doctor;
    }


    /* Mapper  Doctor -> DoctorRepresentation  */
    static public DoctorRepresentation getDoctorRepresentation(Doctor doctor){
        DoctorRepresentation doctorRepresentation = new DoctorRepresentation();
        doctorRepresentation.setName(doctor.getName());
        doctorRepresentation.setUsername(doctor.getUsername());
        doctorRepresentation.setPassword(doctor.getPassword());
        doctorRepresentation.setSpecialty(doctor.getSpecialty());
        doctorRepresentation.setNumOfPatients(doctor.getNumOfPatients());
        doctorRepresentation.setDescription(doctor.getDescription());
        doctorRepresentation.setActive(doctor.isActive());
        doctorRepresentation.setUri("http://localhost:9000/app/doctor/"+doctor.getId());

        return doctorRepresentation;
    }


}
