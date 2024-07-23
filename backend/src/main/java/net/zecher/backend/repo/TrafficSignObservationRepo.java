package net.zecher.backend.repo;

import net.zecher.backend.model.TrafficSignObservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrafficSignObservationRepo extends JpaRepository<TrafficSignObservation, Long> {

    List<TrafficSignObservation> findByObservationType(String observationType);

    List<TrafficSignObservation> findByObservationTypeAndValue(String observationType, String value);

    void deleteAll();
}
