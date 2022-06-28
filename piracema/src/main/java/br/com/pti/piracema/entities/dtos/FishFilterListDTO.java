package br.com.pti.piracema.entities.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FishFilterListDTO {
    private Long id;
    private String pitTag;
    private String scientificName;
    private String captureSpot;
    private LocalDateTime releaseDate;
    private String releaseSpot;
    private String dnaSample;
    private Boolean recapture;
}
