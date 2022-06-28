package br.com.pti.piracema.entities.dtos;

import java.time.LocalDateTime;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassImportFileDTO {

    @CsvBindByName(column = "antennaId")
    private Long antennaId;
    
    @CsvBindByName(column = "pittag")
    private String pitTag;

    @CsvBindByName(column = "datapassagem")
    @CsvDate(value = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime passDate;

}
