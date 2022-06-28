package br.com.pti.piracema.entities.dtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FishDTO {
    private Long id;
    private String pitTag;
    private LocalDateTime registryDate;
    private String scientificName;
    private Double standardLength;
    private Double totalLength;
    private String captureSpot;
    private Double releaseWeight;
    private LocalDateTime releaseDate;
    private String releaseSpot;
    private String dnaSample;
    private Boolean recapture=false;
    private List<PassNoFishDTO> passes;
}
