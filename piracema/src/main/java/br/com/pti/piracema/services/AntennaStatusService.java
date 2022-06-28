package br.com.pti.piracema.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pti.piracema.entities.Antenna;
import br.com.pti.piracema.entities.AntennaStatus;
import br.com.pti.piracema.entities.dtos.AntennaStatusFormDTO;
import br.com.pti.piracema.repositories.AntennaStatusRepository;

@Service
@Transactional
public class AntennaStatusService {
    
    private final AntennaStatusRepository antennaStatusRepository;
    private final AntennaService antennaService;

    public AntennaStatusService(AntennaStatusRepository antennaStatusRepository, AntennaService antennaService) {
        this.antennaStatusRepository = antennaStatusRepository;
        this.antennaService = antennaService;
    }

    public AntennaStatus save(AntennaStatusFormDTO antennaStatusDTO){
        Antenna antenna = antennaService.findById(antennaStatusDTO.getAntennaId());
        AntennaStatus newStatus = new AntennaStatus();
        newStatus.setRegistryDate(antennaStatusDTO.getRegistryDate());
        newStatus.setStatus(antennaStatusDTO.getStatus());
        newStatus.setStatusChangeDate(antennaStatusDTO.getStatusChangeDate());
        antenna.getAntennaStatus().add(newStatus);
        newStatus.setAntennaStatus(antenna);
        return antennaStatusRepository.save(newStatus);

    }

    public List<AntennaStatus> findAll(){
        List<AntennaStatus> antennaStatus = antennaStatusRepository.findAll();
        if(antennaStatus.isEmpty()){
            throw new NoSuchElementException();
        } else {
            return antennaStatus;
        }
    }
    public AntennaStatus findById(Long id) {
        Optional<AntennaStatus> optAntennaStatus = antennaStatusRepository.findById(id);
        if (optAntennaStatus.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optAntennaStatus.get();
    }
}
