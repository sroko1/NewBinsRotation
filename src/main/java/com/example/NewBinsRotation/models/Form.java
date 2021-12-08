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
    @JoinColumn(name = "ID",insertable = false,updatable = false)
    private Truck truck;

    @ManyToOne
    @JoinColumn(name = "ID",insertable = false,updatable = false)
    private Inbound inbound;
    @ManyToOne
    private Supplier suppliers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "form_bin_features",
            joinColumns = @JoinColumn(name = "bin_features_ID"),
            inverseJoinColumns = @JoinColumn(name = "form_ID")
    )
    private List<BinFeature> binsFeatures;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "trucks_form",
            joinColumns = @JoinColumn(name = "T_REG_NUMBER"),
            inverseJoinColumns = @JoinColumn(name = "F_REG_NUMBER")
    )
    private List<Truck> trucks;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "inbounds_form",
            joinColumns = @JoinColumn(name = "F_ID"),
            inverseJoinColumns = @JoinColumn(name = "I_ID")
    )
    private List<Inbound> inbounds;


}
