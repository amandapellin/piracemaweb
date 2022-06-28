package br.com.pti.piracema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pti.piracema.entities.AntennaStatus;

@Repository
public interface AntennaStatusRepository extends JpaRepository<AntennaStatus, Long>{
    
}
