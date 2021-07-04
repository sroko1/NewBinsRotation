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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "CHECKED_AT")
    private LocalDateTime checkedAt;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "VOLUME_AMOUNT")
    private double volumeAmount;


    @ManyToMany(mappedBy = "outbounds", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<BinFeature> binsFeatures;


}
