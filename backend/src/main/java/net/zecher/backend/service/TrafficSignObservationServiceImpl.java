package net.zecher.backend.service;

import net.zecher.backend.dto.TrafficSignObservationDto;
import net.zecher.backend.model.ObservationType;
import net.zecher.backend.repo.ObservationClusterRepo;
import net.zecher.backend.repo.ObservationTypeRepo;
import net.zecher.backend.repo.TrafficSignObservationRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrafficSignObservationServiceImpl implements TrafficSignObservationService {

    private static final int CLUSTER_RADIUS = 20;

    private final ObservationTypeRepo typeRepo;
    private final ObservationClusterRepo clusterRepo;
    private final TrafficSignObservationRepo observationRepo;

    public TrafficSignObservationServiceImpl(ObservationTypeRepo typeRepo, ObservationClusterRepo clusterRepo, TrafficSignObservationRepo observationRepo) {
        this.typeRepo = typeRepo;
        this.clusterRepo = clusterRepo;
        this.observationRepo = observationRepo;
    }

    @Override
    public TrafficSignObservationDto getTrafficSignObservation(long id) {
        return null;
    }

    @Override
    public List<TrafficSignObservationDto> getTrafficSignObservations() {
        return List.of();
    }

    @Override
    public TrafficSignObservationDto addTrafficSignObservation(TrafficSignObservationDto observationDto) {
        // TODO: If new observation is added, create cluster for it unless there is no appropriate cluster for it
        return null;
    }
}
