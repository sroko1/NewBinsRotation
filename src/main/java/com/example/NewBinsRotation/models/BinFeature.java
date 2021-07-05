package com.example.NewBinsRotation.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "binFeatures")
public class BinFeature implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "WIDTH")
    private double width;
    @Column(name = "LENGTH")
    private double length;
    @Column(name = "HEIGHT ")
    private double height;
    @Column(name = "PRICE ")
    private double price;
    @Column(name = "AMOUNT ")
    private int amount;
    @Column(name = "LEASING_PRICE ")
    private double leasingPrice;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "bin_features",
            joinColumns = {
                    @JoinColumn(name = "bin_features_ID", referencedColumnName = "ID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "inbounds_ID", referencedColumnName = "ID",
                            nullable = false, updatable = false)})
    private List<Inbound> inbounds;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "bin_features",
            joinColumns = {
                    @JoinColumn(name = "bin_features_ID", referencedColumnName = "ID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "outbounds_ID", referencedColumnName = "ID",
                            nullable = false, updatable = false)})
    private List<Outbound> outbounds;


}
//PRIMARY KEY(bin_features_ID, inbounds_ID)

