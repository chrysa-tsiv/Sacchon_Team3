package com.codehub.model;

import com.codehub.common.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private  String name;

    @Column(unique = true)
    private  String username;

    private  String password;
    private Gender gender;
    private  LocalDate dob;
    private double height;
    private  double weight;
    private LocalDate signUpDate;
    private  LocalDate first_entry;
    private  String history;
    private  boolean active;
    private  boolean available;
    private boolean needConsulting; //ALLAGI

    //WE WILL MODIFY CONSTRUCTOR'S ARGUMENTS LATER
    public Patient (String name, String username, String password, Gender gender, LocalDate dob, double height, double weight, String history) {

        this.name = name;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.height = height;
        this.weight = weight;
        this.signUpDate = LocalDate.now();  /* auto set*/
        this.available = false;
        this.history = history;
        this.gender = gender;
        this.active = true;
        this.first_entry=first_entry;
        this.needConsulting = false; // TH
    }


    @ManyToOne
    private Doctor doctor;


    @OneToMany(mappedBy="patient",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Measurement> measurement;

    @OneToMany(mappedBy="patient",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;


    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", gender='" + gender + '\'' +
                ", first_entry='" + first_entry + '\'' +
                ", available='" + available + '\'' +
                ", dob=" + dob +
                '}';
    }


}
