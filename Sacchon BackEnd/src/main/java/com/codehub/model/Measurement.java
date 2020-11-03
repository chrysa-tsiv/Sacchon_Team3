package com.codehub.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private double glucoseLevel;
    private double carbIntake;
    private String meds;
    private LocalDate date;


    public Measurement(LocalDate date, double glucose_lvl, double carb_intake,String meds) {
        this.date = date;
        this.glucoseLevel = glucose_lvl;
        this.carbIntake = carb_intake;
        this.meds = meds;
    }

    @ManyToOne
    private Patient patient;

    @Override
    public String toString() {
        return "Measurement{ " +
                ", id='" + id + '\'' +
                ", glucose='" + glucoseLevel + '\'' +
                ", carbs='" + carbIntake + '\'' +
                ", meds='" + meds + '\'' +
                ", measurementDate='" + date + '\'' +
                '}';
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}
