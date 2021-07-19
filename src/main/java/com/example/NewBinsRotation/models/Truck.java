package com.example.NewBinsRotation.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trucks")
public class Truck implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_ID")
    private int id;
    @Column(name = "TRAILER_TYPE")
    private String trailerType;
    @Column (name = "REG_NUMBER")
    private String regNumber;
    @Column (name = "TRAILER_WIDTH")
    private double trailerWidth;
    @Column (name = "TRAILER_LENGTH")
    private double  trailerLength;
    @Column (name = "TRAILER_HEIGHT")
    private double trailerHeight;
    @Column (name = "TRAILER_MAX_VOLUME")
    private double trailerMaxVolume;



    //@OneToMany(mappedBy = "trucks")
    //private List<Inbound>inbounds;



    //@OneToMany(mappedBy = "trucks")
   // private List<Outbound>outbounds;


}
