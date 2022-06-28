package br.com.pti.piracema.entities.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FishNoPassesDTO {
    private Long id;
    private String pitTag;
    private LocalDateTime registryDate;
    private String scientificName;
    private Double totalLength;
    private String captureSpot;
    private Double releaseWeight;
    private LocalDateTime releaseDate;
    private String releaseSpot;
    private String dnaSample;
    private Boolean recapture;
}
