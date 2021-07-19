package com.example.NewBinsRotation.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "inbounds")
public class Inbound implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name= "bin_feat_inbounds",
            joinColumns = @JoinColumn(name = "bin_features_ID"),
            inverseJoinColumns =  @JoinColumn(name = "inbounds_ID")
    )
    private List<BinFeature> binsFeatures;

   @ManyToOne
    @JoinColumn(name = "T_ID", nullable = false)
    private Truck truck;

}
//mysql w trucku  ->INBOUND_ID smallint not null,
//OUTBOUND_ID  smallint not null,
//foreign key (INBOUND_ID) references inbounds(ID),
//foreign key (OUTBOUND_ID) references outbounds(ID)