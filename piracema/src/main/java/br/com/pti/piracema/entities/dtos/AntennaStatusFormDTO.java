package br.com.pti.piracema.entities.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AntennaStatusFormDTO {
    
    private LocalDateTime registryDate;
    private Long antennaId;
    private Boolean status;
    private LocalDateTime statusChangeDate;
}
