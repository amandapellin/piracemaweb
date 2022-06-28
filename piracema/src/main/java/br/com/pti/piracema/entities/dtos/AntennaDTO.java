package br.com.pti.piracema.entities.dtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntennaDTO {
    private Long id;
    private LocalDateTime registryDate;
    private String latitude; 
    private String longitude;
    private LocalDateTime installDate;
    private LocalDateTime deactivationDate; 
    private List<PassDTO> passagem;
    private List<AntennaStatusDTO> antennaStatus;
}
