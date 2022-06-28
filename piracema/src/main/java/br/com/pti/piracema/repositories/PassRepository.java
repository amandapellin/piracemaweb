package br.com.pti.piracema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pti.piracema.entities.Pass;

@Repository
public interface PassRepository extends JpaRepository<Pass,Long>{

}
