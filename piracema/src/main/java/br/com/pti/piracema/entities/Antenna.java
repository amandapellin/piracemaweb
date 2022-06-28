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
@Table(name = "antenas")
public class Antenna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String latitude;
    
    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
    private LocalDateTime installDate;

    @Column(nullable = true)
    private LocalDateTime deactivationDate; 

    private LocalDateTime registryDate;

    @OneToMany(mappedBy = "antenna", cascade = CascadeType.ALL)
    private List<Pass> passes;

    @OneToMany(targetEntity = AntennaStatus.class, mappedBy = "antennaStatus", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AntennaStatus> antennaStatus;

    @PrePersist
    public void create() {
        registryDate = LocalDateTime.now();
    }
    @PreUpdate
    public void update() {
       registryDate = LocalDateTime.now();
    }
    
}
