package br.com.pti.piracema.controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pti.piracema.entities.AntennaStatus;
import br.com.pti.piracema.entities.dtos.AntennaStatusDTO;
import br.com.pti.piracema.entities.dtos.AntennaStatusFormDTO;
import br.com.pti.piracema.services.AntennaStatusService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*" )
@RestController
@RequestMapping("/statusantenna")
@Slf4j
public class AntennaStatusController {
    private final AntennaStatusService antennaStatusService;
    private final ModelMapper modelMapper;

    public AntennaStatusController(AntennaStatusService antennaStatusService, ModelMapper modelMapper) {
        this.antennaStatusService = antennaStatusService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<AntennaStatusDTO> create(@RequestBody AntennaStatusFormDTO antennaStatusFormDTO){
        try{
            AntennaStatus antennaStatus = antennaStatusService.save(antennaStatusFormDTO);
            AntennaStatusDTO antennaStatusDTO = modelMapper.map(antennaStatus, AntennaStatusDTO.class);
            return ResponseEntity.ok(antennaStatusDTO);
        } catch (Exception e){
            return ResponseEntity.status(400).build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<AntennaStatusDTO>> getAll() {
        List<AntennaStatus> antennaStatus = antennaStatusService.findAll();
        List<AntennaStatusDTO> antennaStatusDTO = antennaStatus.stream()
                                    .map(a-> modelMapper.map(a, AntennaStatusDTO.class))
                                    .collect(Collectors.toList());

        return ResponseEntity.ok(antennaStatusDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AntennaStatusDTO>getById(@PathVariable Long id){

        try{
            AntennaStatus antennaStatus = antennaStatusService.findById(id);
            return ResponseEntity.ok(modelMapper.map(antennaStatus, AntennaStatusDTO.class));
        }catch (Exception e){
            log.error("Status não encontrado", e);
            return ResponseEntity.notFound().build();
        }
    }

}
