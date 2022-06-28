package br.com.pti.piracema.entities.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassFormDTO {
    private LocalDateTime registryDate;
    private Long fishId;
    private Long antennaId;
}
