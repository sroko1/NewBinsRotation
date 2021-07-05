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

@Table(name = "inbounds")
public class Inbound implements Serializable {
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

    @ManyToMany
    @JoinTable(
            name= "bin_feat_inbounds",
            joinColumns = @JoinColumn(name = "bin_features_ID"),
            inverseJoinColumns =  @JoinColumn(name = "inbounds_ID")
    )


    //@ManyToMany(mappedBy = "inbounds", fetch = FetchType.LAZY, cascade = {
      //      CascadeType.PERSIST,
       //     CascadeType.MERGE
   // })
    private List<BinFeature> binsFeatures;


}
