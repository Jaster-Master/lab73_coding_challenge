package net.zecher.backend.service;

import net.zecher.backend.GeoUtils;
import net.zecher.backend.ObservationType;
import net.zecher.backend.dto.TrafficSignObservationDto;
import net.zecher.backend.model.TrafficSignObservation;
import net.zecher.backend.repo.TrafficSignObservationRepo;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrafficSignObservationServiceImpl implements TrafficSignObservationService {

    private static final double CLUSTER_RADIUS = 0.05; // km

    private final TrafficSignObservationRepo observationRepo;

    public TrafficSignObservationServiceImpl(TrafficSignObservationRepo observationRepo) {
        this.observationRepo = observationRepo;
    }

    @Override
    public TrafficSignObservationDto getTrafficSignObservation(long id) {
        var result = observationRepo.getReferenceById(id);
        var mapper = new ModelMapper();
        return mapper.map(result, TrafficSignObservationDto.class);
    }

    @Override
    public List<TrafficSignObservationDto> getTrafficSignObservations(ObservationType type, String value) {
        var typeName = type == null ? null : type.name();
        List<TrafficSignObservation> observations;
        if (typeName != null && value != null) {
            observations = observationRepo.findByObservationTypeAndValue(typeName, value);
        } else if (typeName != null) {
            observations = observationRepo.findByObservationType(typeName);
        } else {
            observations = observationRepo.findAll();
        }
        var mapper = new ModelMapper();
        List<TrafficSignObservationDto> observationDtos = new ArrayList<>();
        for (TrafficSignObservation observationDto : observations) {
            observationDtos.add(mapper.map(observationDto, TrafficSignObservationDto.class));
        }
        return observationDtos;
    }

    @Override
    public TrafficSignObservationDto addTrafficSignObservation(TrafficSignObservationDto observationDto) {
        var mapper = new ModelMapper();
        var observations = observationRepo.findByObservationTypeAndValue(observationDto.getType().name(), observationDto.getValue());
        for (TrafficSignObservation observation : observations) {
            if (GeoUtils.haversine(observationDto.getLatitude(), observationDto.getLongitude(), observation.getLatitude(), observation.getLongitude()) < CLUSTER_RADIUS) {
                double newLatitude = (observationDto.getLatitude() + observation.getLatitude()) / 2;
                double newLongitude = (observationDto.getLongitude() + observation.getLongitude()) / 2;
                observation.setLatitude(newLatitude);
                observation.setLongitude(newLongitude);
                observationRepo.save(observation);
                return mapper.map(observation, TrafficSignObservationDto.class);
            }
        }

        var observation = mapper.map(observationDto, TrafficSignObservation.class);
        observation = observationRepo.save(observation);
        return mapper.map(observation, TrafficSignObservationDto.class);
    }
}
