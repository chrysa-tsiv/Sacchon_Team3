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
public class Appointment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    private String consultation;
    private String edit;

    public Appointment(LocalDate date, String consultation, String edit) {
        this.date = date;
        this.consultation = consultation;
        this.edit = edit;

    }

    @Override
    public String toString() {
        return "Appointment{" +
                ", date='" + date + '\'' +
                ", consultation='" + consultation + '\'' +
                ", edit='" + edit + '\'' +
                '}';
    }

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

}
