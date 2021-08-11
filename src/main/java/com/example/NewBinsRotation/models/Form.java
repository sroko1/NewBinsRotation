package com.example.NewBinsRotation.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "form")
public class Form implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "AMOUNT")
    private int amount;
    @ManyToOne
    private BinFeature binFeatures;
    @ManyToOne
    private Truck trucks;
    @ManyToOne
    private Inbound inbounds;
    @ManyToOne
    private Supplier suppliers;
}
