package br.com.pti.piracema.entities.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntennaInstalationDTO {
    
    private Long id;
    private LocalDateTime installDate;
    private LocalDateTime deactivationDate;
}

//retorna info sobre a instalação da antena
