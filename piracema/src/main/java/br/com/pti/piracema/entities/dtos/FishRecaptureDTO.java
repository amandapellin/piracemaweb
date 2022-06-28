package br.com.pti.piracema.entities.dtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FishRecaptureDTO {
    
    private LocalDateTime registryDate;
    private String scientificName;
    private Double standardLength;
    private Double totalLength;
    private String captureSpot;
    private Double releaseWeigth;
    private LocalDateTime releaseDate;
    private String releaseSpot;
    private String dnaSample;
    private Boolean recapture = true;
    private List<PassNoFishDTO> passes;

}
