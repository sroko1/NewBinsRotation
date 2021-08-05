package com.example.NewBinsRotation.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "outbounds")
public class Outbound implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "AMOUNT")
    private int amount;
    @Column(name = "CHECKED_AT")
    private LocalDateTime checkedAt;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "VOLUME_AMOUNT")
    private double volumeAmount;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name= "bin_feat_outbounds",
            joinColumns = @JoinColumn(name = "bin_features_ID"),
            inverseJoinColumns =  @JoinColumn(name = "outbounds_ID")
    )
    private List<BinFeature> binsFeatures;

    @ManyToOne
    @JoinColumn(name = "T_ID",nullable = false)
    private Truck truck;
}
