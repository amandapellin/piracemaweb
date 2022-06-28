package br.com.pti.piracema.services;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.pti.piracema.entities.Antenna;
import br.com.pti.piracema.entities.Fish;
import br.com.pti.piracema.entities.Pass;
import br.com.pti.piracema.entities.dtos.PassFormDTO;
import br.com.pti.piracema.entities.dtos.PassImportFileDTO;
import br.com.pti.piracema.repositories.PassRepository;

@Service
@Transactional
public class PassService {
    private final PassRepository passRepository;
    private final FishService fishService;
    private final AntennaService antennaService;
    private final PassImportFileService passImportFileService;

    public PassService(PassRepository passRepository, AntennaService antennaService,
                                     FishService fishService, PassImportFileService passImportFileService) {
                
        this.passRepository = passRepository;
        this.fishService = fishService;
        this.antennaService = antennaService;
        this.passImportFileService = passImportFileService;

    }

    public Pass create(PassFormDTO passDTO) {
        Fish fish = fishService.findById(passDTO.getFishId());
        Antenna antenna = antennaService.findById(passDTO.getAntennaId());
        Pass pass = new Pass();
        fish.getPasses().add(pass);
        pass.setFish(fish);
        antenna.getPasses().add(pass);
        pass.setAntenna(antenna);
        return passRepository.save(pass);
    }

    public Pass create(PassImportFileDTO passDTO) {
        Fish fish = fishService.findByPitTag(passDTO.getPitTag()); 
        Antenna antenna = antennaService.findById(passDTO.getAntennaId()); 
        Pass pass = new Pass();
        pass.setFish(fish);
        pass.setAntenna(antenna);
        pass.setPassDate(passDTO.getPassDate());
        fish.getPasses().add(pass);
       
        antenna.getPasses().add(pass);
       
        return passRepository.save(pass);
    }
    public List<Pass> upload(MultipartFile file) throws IOException{

        List<PassImportFileDTO> passImportFileDTO = passImportFileService.parse(file);

        return passImportFileDTO.stream().map(c->create(c)).collect(Collectors.toList());
    }
   
    public Pass save(Pass pass){
        return passRepository.save(pass);
    }

    public List<Pass> findAll(){
        return passRepository.findAll();
    }

    public Pass findById(Long id) {
        Optional<Pass> optPass = passRepository.findById(id);
        if (optPass.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optPass.get();
    }
    public void deleteById(Long id) {
        try{
            passRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new NoSuchElementException();
        }
    }
    
}
