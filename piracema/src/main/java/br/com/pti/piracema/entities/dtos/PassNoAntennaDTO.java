package br.com.pti.piracema.entities.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassNoAntennaDTO {
    
    private Long id;
    private LocalDateTime registryDate;
    private FishNoPassesDTO fish;
}
