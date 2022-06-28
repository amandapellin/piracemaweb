package br.com.pti.piracema.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pti.piracema.entities.Fish;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {
    
    
    Optional<Fish> findTopByPitTagOrderByIdDesc(String pittag);
    List<Fish> findAllByPitTagOrderByIdDesc(String pittag);

    Optional<Fish> findTopByScientificNameOrderByIdDesc(String scientificName);
    List<Fish> findAllByScientificName(String scientificName);

    List<Fish> findAllByCaptureSpot(String captureSpot);

    List<Fish> findAllByReleaseSpot(String releaseSpot);

    List<Fish> findAllByDnaSample(String dnaSample);

    Optional<Fish> findTopByDnaSampleOrderByIdDesc(String dnaSample);
}
