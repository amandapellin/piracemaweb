package br.com.pti.piracema.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fish")
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime registryDate;

    @Column(nullable = false)
    private String pitTag;

    @Column(nullable = false)
    private String scientificName;

    @Column(nullable = false)
    private Double standardLength;

    @Column(nullable = false)
    private Double totalLength;

    @Column(nullable = false)
    private String captureSpot;

    @Column(nullable = false)
    private Double releaseWeight;

    @Column(nullable = false)
    private LocalDateTime releaseDate;

    @Column(nullable = false)
    private String releaseSpot;
    
    @Column(nullable = false)
    private String dnaSample;

    @Column
    private Boolean recapture;

    @Column
    private Boolean inactive;

    @OneToMany(targetEntity = Pass.class, mappedBy = "fish", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pass> passes;
    
    @PrePersist
    public void create() {
        registryDate = LocalDateTime.now();
        inactive = false;
    }
    @PreUpdate
    public void update() {
       registryDate = LocalDateTime.now();
    }
}
