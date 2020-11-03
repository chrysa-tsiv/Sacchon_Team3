package com.codehub.model;

import com.codehub.security.CustomRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChiefAdministrator {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private CustomRole role;


    public ChiefAdministrator(String username, String password, CustomRole role) {
        this.username = username;
        this.password = password;
        this.role = CustomRole.ROLE_ADMIN;
    }

}
