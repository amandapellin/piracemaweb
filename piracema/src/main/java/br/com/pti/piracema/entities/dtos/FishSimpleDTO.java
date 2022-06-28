package br.com.pti.piracema.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FishSimpleDTO {
    private Long id;
    private String scientificName;
    private String pitTag;
    private Double totalLength;
    private Double releaseWeight;
    private String dnaSample;
    private Boolean recapture;
}
