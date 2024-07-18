package net.zecher.backend.repo;

import net.zecher.backend.model.ObservationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservationTypeRepo extends JpaRepository<ObservationType, Long> {

    ObservationType findByObservationType(String observationType);
}
