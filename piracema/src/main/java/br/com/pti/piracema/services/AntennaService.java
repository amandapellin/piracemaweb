package br.com.pti.piracema.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pti.piracema.entities.Antenna;
import br.com.pti.piracema.repositories.AntennaRepository;

@Service
@Transactional
public class AntennaService {
    private final AntennaRepository antennaRepository;

    public AntennaService(AntennaRepository antennaRepository) {
        this.antennaRepository = antennaRepository;
    }

    public Antenna save(Antenna antenna) {
        return antennaRepository.save(antenna);
    }

    public List<Antenna> findAll(){
        List<Antenna> antenna = antennaRepository.findAll();
        if(antenna.isEmpty()){
            throw new NoSuchElementException();
        } else {
            return antenna;
        }
    }

    public Antenna findById(Long id) {
        Optional<Antenna> antenna = antennaRepository.findById(id);
        if (antenna.isEmpty()){
            throw new NoSuchElementException();
        }
        return antenna.get();
    }

    public void deleteById(Long id) {
        Antenna antenna = findById(id);
        antennaRepository.delete(antenna);
    }
    
    
}
