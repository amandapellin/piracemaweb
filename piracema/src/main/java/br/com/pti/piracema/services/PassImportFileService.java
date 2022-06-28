package br.com.pti.piracema.services;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import br.com.pti.piracema.entities.dtos.PassImportFileDTO;

@Service
@Transactional
public class PassImportFileService {
    public List<PassImportFileDTO> parse(MultipartFile file) throws IOException{
            Reader reader = new InputStreamReader(file.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).build();

            CsvToBean<PassImportFileDTO> csvToBean = new CsvToBeanBuilder(csvReader)
                                                        .withSeparator(',')
                                                        .withType(PassImportFileDTO.class)
                                                        .build();

            List<PassImportFileDTO> passImportFileDTO = csvToBean.parse();
    
            return passImportFileDTO;
    }
}
