package br.com.pti.piracema.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
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

import br.com.pti.piracema.entities.Antenna;
import br.com.pti.piracema.entities.dtos.AntennaDTO;
import br.com.pti.piracema.entities.dtos.AntennaLocationDTO;
import br.com.pti.piracema.entities.dtos.AntennaNoPassesDTO;
import br.com.pti.piracema.services.AntennaService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*" )
@RestController
@RequestMapping("/antenas")
@Slf4j
public class AntennaController {
    private AntennaService antennaService;
    private ModelMapper modelMapper;

    public AntennaController(AntennaService antennaService, ModelMapper modelMapper){
        this.antennaService = antennaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<AntennaNoPassesDTO> create(@RequestBody Antenna antenna){
        try { 
            Antenna savedAntenna = antennaService.save(antenna);
            AntennaNoPassesDTO antennaDTO = modelMapper.map(savedAntenna, AntennaNoPassesDTO.class);
            return ResponseEntity.ok(antennaDTO);
        } catch(DataIntegrityViolationException e) {
                log.error("Antena já inserida", e);
                return ResponseEntity.badRequest().build();
        } catch(Exception e) {
                log.error("Falha durante inserção da antena", e);
                return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<AntennaLocationDTO>> getAll(){
        List<Antenna> antennas = antennaService.findAll();
        List<AntennaLocationDTO> antennaLocationDTOs = antennas.stream()
                                        .map(a -> modelMapper.map(a, AntennaLocationDTO.class))
                                        .collect(Collectors.toList());
        return ResponseEntity.ok(antennaLocationDTOs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AntennaDTO> getById(@PathVariable Long id) {
        try {
            Antenna antenna = antennaService.findById(id);
            System.out.println(antenna.getId());
            System.out.printf(String.valueOf(AntennaDTO.class));
            return ResponseEntity.ok(modelMapper.map(antenna, AntennaDTO.class));
        } catch(Exception e) {
            log.error("Antena não encontrada",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<AntennaDTO> update(@PathVariable Long id, @RequestBody Antenna antenna){
        try {
            Antenna foundAntenna = antennaService.findById(id);
            modelMapper.map(antenna, foundAntenna);
            antennaService.save(foundAntenna);
            return ResponseEntity.ok(modelMapper.map(foundAntenna,AntennaDTO.class));
        } catch(Exception e) {
            log.error("Falha durante atualização", e);
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            antennaService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            log.error("Falha durante remoção", e);
            return ResponseEntity.notFound().build();
        }
    }

}
