package br.com.pti.piracema.services;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pti.piracema.entities.Fish;
import br.com.pti.piracema.repositories.FishRepository;

@Service
@Transactional
public class FishService {
    
    private final FishRepository fishRepository;

    public FishService(FishRepository fishRepository){
        this.fishRepository = fishRepository;
    }
    
    public Fish save(Fish fish, Boolean update){
       
        Optional<Fish> optionalFish = fishRepository.findTopByPitTagOrderByIdDesc(fish.getPitTag());

        Optional<Fish> optionalFishDna = fishRepository.findTopByDnaSampleOrderByIdDesc(fish.getDnaSample());

        if(optionalFish.isPresent()) {
            Fish foundFish = optionalFish.get();

            if(!update){
                if(foundFish.getPitTag().equals(fish.getPitTag()) && !fish.getRecapture()){
                    throw new DataIntegrityViolationException("Peixe já existe e não é uma recaptura");
                }
            }
        }

        if(optionalFishDna.isPresent()) {
            Fish foundFish = optionalFishDna.get();

            if(!update){
                if(foundFish.getDnaSample().equals(fish.getDnaSample()) && !fish.getRecapture()){
                    throw new DataIntegrityViolationException("Peixe já existe e não é uma recaptura");
                }
            }
        }

        return fishRepository.save(fish);
    }
    public List<Fish> findAll(){
        return fishRepository.findAll();
    }

    public Fish findById(Long id){
        Optional<Fish> optFish = fishRepository.findById(id);

        if (optFish.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optFish.get();
    }

    public Fish findByPitTag(String pitTag) {
        Optional<Fish> optFish = fishRepository.findTopByPitTagOrderByIdDesc(pitTag);
        if (optFish.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optFish.get();
    }

    public List<Fish> findAllByPitTag(String pitTag) {

        List<Fish> fishList = fishRepository.findAllByPitTagOrderByIdDesc(pitTag);

        if (fishList.isEmpty()){
            throw new NoSuchElementException();
        }
        return fishList;
    }

   /*  public Fish findByScientificName(String scientificName) {
        Optional<Fish> optFish = fishRepository.findTopByScientificNameOrderByIdDesc(scientificName);
        /*if (optFish.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optFish.get();
    }  */
    public List<Fish> findAllByScientificName(String scientificName) {

        List<Fish> fishList = fishRepository.findAllByScientificName(scientificName);

       /* if (fishList.isEmpty()){
            throw new NoSuchElementException();
        } */
        return fishList;
    }

    public List<Fish> findAllByCaptureSpot(String captureSpot) {
        List<Fish> fishList = fishRepository.findAllByCaptureSpot(captureSpot);
        if (fishList.isEmpty()){
            throw new NoSuchElementException();
        }
        return fishList;
    }

    public List<Fish> findAllByReleaseSpot(String releaseSpot) {
        List<Fish> fishList = fishRepository.findAllByReleaseSpot(releaseSpot);

        if (fishList.isEmpty()){
            throw new NoSuchElementException();
        }
        return fishList;
    }

    public List<Fish> findAllByDnaSample(String dnaSample) {
        List<Fish> fishList = fishRepository.findAllByDnaSample(dnaSample);

       /* if (fishList.isEmpty()){
            throw new NoSuchElementException();
        }*/
        return fishList;
    }
    public void deleteById(Long id) {
        Fish fish = findById(id);
        fishRepository.delete(fish);
    }

    
}
