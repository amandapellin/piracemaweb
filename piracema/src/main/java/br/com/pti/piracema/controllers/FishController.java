package br.com.pti.piracema.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pti.piracema.entities.Fish;
import br.com.pti.piracema.entities.dtos.FishDTO;
import br.com.pti.piracema.entities.dtos.FishNoPassesDTO;
import br.com.pti.piracema.entities.dtos.FishSimpleDTO;
import br.com.pti.piracema.services.FishService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*" )
@RestController
@RequestMapping("/fish")
@Slf4j
public class FishController {
    private final FishService fishService;
    private final ModelMapper modelMapper;

    private Boolean update = false;

    public FishController(ModelMapper modelMapper, FishService fishService){
        this.fishService = fishService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<FishNoPassesDTO> create(@RequestBody Fish fish){
        try {
            Fish savedFish = fishService.save(fish, update);
            FishNoPassesDTO fishDTO = modelMapper.map(savedFish, FishNoPassesDTO.class);
            return ResponseEntity.ok(fishDTO);
        } catch(Exception e) {
            log.error("Falha durante inserção", e);
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/multipleinserts")
    public ResponseEntity<List<FishNoPassesDTO>> create(@RequestBody List<Fish> fishes){
        try{
            List<FishNoPassesDTO> fishNoPsDTO =  fishes.stream()
                                                    .map(f -> modelMapper
                                                    .map(fishService
                                                    .save(f, update), FishNoPassesDTO.class))
                                                    .collect(Collectors.toList());
            return ResponseEntity.ok(fishNoPsDTO);
        }catch (Exception e){
            log.error("Falha durante inserção", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<FishSimpleDTO>> findAll() {
        List<Fish> fishes = fishService.findAll();
        if (fishes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        List<FishSimpleDTO> fishDTO = fishes.stream()
                                        .map(c -> modelMapper.map(c, FishSimpleDTO.class))
                                        .collect(Collectors.toList());
        return ResponseEntity.ok(fishDTO);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FishDTO> find(@PathVariable Long id) {
        try {
            Fish fish = fishService.findById(id);
            return ResponseEntity.ok(modelMapper.map(fish,FishDTO.class));
        } catch(Exception e) {
            log.error("Peixe não encontrado", e);
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/pitTag/{pitTag}")
    public ResponseEntity<FishDTO> findByPitTag(@PathVariable String pitTag) {
        try {
            Fish fish = fishService.findByPitTag(pitTag);
            return ResponseEntity.ok(modelMapper.map(fish,FishDTO.class));
        } catch(Exception e) {
            log.error("Peixe não encontradro", e);
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/pitTags/{pitTag}")
    public ResponseEntity<List<FishDTO>> findAllByPitTag(@PathVariable String pitTag) {
        try {
            List<Fish> fishList = fishService.findAllByPitTag(pitTag);

            List<FishDTO> fishDTOList = fishList.stream()
                                        .map(c ->modelMapper
                                        .map(c, FishDTO.class))
                                        .collect(Collectors.toList());

            return ResponseEntity.ok(fishDTOList);

        } catch(Exception e) {
            log.error("Peixe não encontrado", e);
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/scientificName/{scientificName}")
    public ResponseEntity<List<FishDTO>> findAllByScientificName(@PathVariable String scientificName) {
        try {
            List<Fish> fishList = fishService.findAllByScientificName(scientificName);
            
            List<FishDTO> fishDTOList = fishList.stream()
                                            .map(c ->modelMapper
                                            .map(c, FishDTO.class))
                                            .collect(Collectors.toList());

            return ResponseEntity.ok(fishDTOList);
        } catch(Exception e) {
            log.error("Nome científico não encontrado", e);
            return ResponseEntity.notFound().build();
        }
    }
    /*@GetMapping("/scientName/{scientName}")
    public ResponseEntity<FishDTO> findByScientificName(@PathVariable String scientificName) {
        try {
            Fish fish = fishService.findByScientificName(scientificName);
            return ResponseEntity.ok(modelMapper.map(fish,FishDTO.class));
        } catch(Exception e) {
            log.error("Peixe não encontradro", e);
            return ResponseEntity.notFound().build();
        }
    }*/

    @GetMapping("/capture/{captureSpot}")
    public ResponseEntity<List<FishDTO>> findAllByCaptureSpot(@PathVariable String captureSpot) {
        try {
            List<Fish> fishList = fishService.findAllByCaptureSpot(captureSpot);
            List<FishDTO> fishDTOList = fishList.stream()
                                        .map(c->modelMapper
                                        .map(c, FishDTO.class))
                                        .collect(Collectors.toList());
        
            return ResponseEntity.ok(fishDTOList);
        } catch(Exception e) {
            log.error("O local informado não possui registro de captura", e);
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/releasespot/{releasespot}")
    public ResponseEntity<List<FishDTO>> findReleaseDate(@PathVariable String releaseSpot){
        try{
            List<Fish> fishList = fishService.findAllByReleaseSpot(releaseSpot);
           
            List<FishDTO> fishDTOList = fishList.stream()
                                        .map(c->modelMapper
                                        .map(c,FishDTO.class))
                                        .collect(Collectors.toList());
            return ResponseEntity.ok(fishDTOList);

        }catch(Exception e) {
            log.error("O local informado não possui registro de soltura", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dnaSample/{dnaSample}")
    public ResponseEntity<List<FishDTO>> findByDnaSample(@PathVariable String dnaSample) {
        try {
            List<Fish> fishList = fishService.findAllByDnaSample(dnaSample);
            
            List<FishDTO> fishDTOList = fishList.stream()
                                        .map(c-> modelMapper
                                        .map(c, FishDTO.class))
                                        .collect(Collectors.toList());

            return ResponseEntity.ok(fishDTOList);
        } catch(Exception e) {
            log.error("Amostra de DNA não encontrada nos registros", e);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FishDTO> update(@PathVariable Long id,
                                        @RequestBody Fish fish) {
        update = true;
        try {
            Fish foundFish = fishService.findById(id);
            modelMapper.map(fish, foundFish);
            List<Fish> fishNames = fishService.findAllByScientificName(foundFish.getScientificName());
            
            if(!foundFish.getScientificName().isEmpty()){
                fishNames.forEach((f) -> {
                    f.setScientificName(foundFish.getScientificName());
                });
            }

            List<Fish> fishList = fishService.findAllByPitTag(foundFish.getPitTag());
            
            if(!foundFish.getPitTag().isEmpty()){
                fishList.forEach((f) -> {
                    f.setPitTag(foundFish.getPitTag());
                });
            }
            if(!foundFish.getTotalLength().toString().isEmpty()){
                fishList.forEach((f) -> {
                    f.setTotalLength(foundFish.getTotalLength());
                });
            }

            fishService.save(foundFish, update);

            update=false;
            return ResponseEntity.ok(modelMapper.map(foundFish,FishDTO.class));

        } catch(Exception e) {
            log.error("Falha durante atualização", e);
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            fishService.deleteById(id); 
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            log.error("Falha durante remoção", e);
            return ResponseEntity.notFound().build();
        }
    }
    
}
