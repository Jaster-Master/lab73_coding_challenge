package net.zecher.backend.repo;

import net.zecher.backend.model.ObservationCluster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservationClusterRepo extends JpaRepository<ObservationCluster, Long> {
}
