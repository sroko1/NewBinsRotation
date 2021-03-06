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
    @Column(name = "VOLUME ")
    private double volume;
    @Column(name = "LEASING_PRICE ")
    private double leasingPrice;



    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "bin_feat_inbounds",
            joinColumns = {
                    @JoinColumn(name = "bin_features_ID", referencedColumnName = "ID",
                            nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "inbounds_I_ID", referencedColumnName = "I_ID",
                            nullable = false)})
    private List<Inbound> inbounds;



    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "bin_feat_outbounds",
            joinColumns = {
                    @JoinColumn(name = "bin_features_ID", referencedColumnName = "ID",
                            nullable = false )},
            inverseJoinColumns = {
                    @JoinColumn(name = "outbounds_O_ID", referencedColumnName = "O_ID",
                            nullable = false)})
    private List<Outbound> outbounds;




    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "form_bin_features",
            joinColumns ={
            @JoinColumn(name = "form_ID", referencedColumnName =  "ID",
            nullable = false)},
            inverseJoinColumns = {
            @JoinColumn(name = "bin_features_ID", referencedColumnName = "ID",
            nullable = false)
            })
    private List<Form>forms;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "outForm_bin_features",
            joinColumns ={
                    @JoinColumn(name = "outForm_ID", referencedColumnName =  "ID",
                            nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "bin_features_ID", referencedColumnName = "ID",
                            nullable = false)
            })
    private List<OutForm>outForms;


}

