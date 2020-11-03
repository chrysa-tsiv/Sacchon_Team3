package com.codehub.model;

import com.codehub.common.MedicalSpecialty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String username;

    private String password;
    private MedicalSpecialty specialty;
    private int numOfPatients;
    private boolean active;
    private String description;

  public Doctor(String name, String username, String password, MedicalSpecialty specialty, String description) {
      this.name = name;
      this.username = username;
      this.password = password;
      this.specialty = specialty;
      this.description = description;
      this.active = true;

      numOfPatients = 2; //PROSWRINA GIA ELEGXO

    }

    @OneToMany(mappedBy="doctor",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Patient> patients;



    @Override
    public String toString() {
        return "Doctor{" +
                 "id=" + id +
                ", name='" + name + '\'' +
                ", medical specialty='" + specialty + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

}