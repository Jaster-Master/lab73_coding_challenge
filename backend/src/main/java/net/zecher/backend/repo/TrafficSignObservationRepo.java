package net.zecher.backend.repo;

import net.zecher.backend.model.TrafficSignObservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficSignObservationRepo extends JpaRepository<TrafficSignObservation, Long> {
}
