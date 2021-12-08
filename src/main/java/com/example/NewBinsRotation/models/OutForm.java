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
@Table(name = "out_form")
public class OutForm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "AMOUNT")
    private int amount;
    @ManyToOne
    private BinFeature binFeatures;
    @ManyToOne
    @JoinColumn(name = "ID",insertable = false, updatable = false)
    private Truck trucks;
    @ManyToOne
    private Outbound outbounds;
    @ManyToOne
    private Supplier suppliers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name= "out_form_bin_features",
            joinColumns = @JoinColumn(name = "bin_features_ID"),
            inverseJoinColumns =  @JoinColumn(name = "out_form_ID")
    )
    private List<BinFeature> binsFeatures;

}
