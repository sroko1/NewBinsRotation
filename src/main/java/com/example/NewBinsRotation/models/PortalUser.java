package com.example.NewBinsRotation.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PU_ID")
    private int id;
    @Column(name = "PU_LOGIN", unique = true)
    private String login;
    @Column(name = "PU_FIRSTNAME")
    private String firstName;
    @Column(name = "PU_LASTNAME")
    private String lastName;
    @Column(name = "PU_EMAIL")
    @Email
    private String email;
    @Column(name = "PU_PASSWORD")
    @Min(7)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "portal_user_roles",
            joinColumns = @JoinColumn(name = "PUR_PU_ID"),
            inverseJoinColumns = @JoinColumn(name = "PUR_RO_ID"))
    private Set<Role> roles;

    //loki
    //rambo
    //sroko1
    //sroka
}