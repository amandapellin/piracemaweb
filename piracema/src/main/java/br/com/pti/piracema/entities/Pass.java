package br.com.pti.piracema.entities;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="passagens")
public class Pass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(targetEntity = Antenna.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_antena")
    private Antenna antenna;

    @ManyToOne(targetEntity = Fish.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_peixe")
    private Fish fish;

    @Column(nullable = true)
    private LocalDateTime passDate;
    
    @Column(nullable = false)
    private LocalDateTime registryDate;

    @PrePersist
    public void create() {
        registryDate = LocalDateTime.now();
    }
    @PreUpdate
    public void update(){
        registryDate = LocalDateTime.now();
    }
    
    
}
