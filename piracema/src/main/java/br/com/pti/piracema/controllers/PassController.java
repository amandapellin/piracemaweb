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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.pti.piracema.entities.Pass;
import br.com.pti.piracema.entities.dtos.PassDTO;
import br.com.pti.piracema.entities.dtos.PassFormDTO;
import br.com.pti.piracema.services.PassService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*" )
@RequestMapping("/passes")
@RestController
@Slf4j
public class PassController {
    
    private PassService passService;
    private ModelMapper modelMapper;

    public PassController(PassService passService, ModelMapper modelMapper){
        this.passService = passService;
        this.modelMapper = modelMapper;
    }
    @PostMapping
    public ResponseEntity<PassDTO> create(@RequestBody PassFormDTO pass){
        try{
            Pass savedPass = passService.create(pass);
            PassDTO passDTO = modelMapper.map(savedPass, PassDTO.class);
            return ResponseEntity.ok(passDTO);
        } catch (Exception e){
            log.error("Falha durante inserção", e);
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/upload")
    public ResponseEntity<List<PassDTO>> createFromCSV(@RequestParam("csvFile") MultipartFile file) {
        try {
            List<Pass> passes = passService.upload(file);

            List<PassDTO> passesDTO = passes.stream()
                    .map(p -> modelMapper.map(p,PassDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(passesDTO);
        } catch(Exception e) {
            log.error("" + e.getMessage());
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PassDTO>> getAll(){
        List<Pass> passes = passService.findAll();
        List<PassDTO> passesDtos = passes
                                .stream()
                                .map(p -> modelMapper.map(p, PassDTO.class))
                                .collect(Collectors.toList());
        return ResponseEntity.ok(passesDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassDTO> getById(@PathVariable Long id){
        try {
            Pass pass = passService.findById(id);
            return ResponseEntity.ok(modelMapper.map(pass,PassDTO.class));
        } catch(Exception e) {
            log.error("Passagem não encontrada", e);
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            passService.deleteById(id); 
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            log.error("Falha durante remoção", e);
            return ResponseEntity.notFound().build();
        }
    }
}
