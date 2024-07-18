package net.zecher.backend.service;

import net.zecher.backend.ObservationType;
import net.zecher.backend.dto.TrafficSignObservationDto;

import java.util.List;

public interface TrafficSignObservationService {

    TrafficSignObservationDto getTrafficSignObservation(long id);
    List<TrafficSignObservationDto> getTrafficSignObservations(ObservationType type, String value);
    TrafficSignObservationDto addTrafficSignObservation(TrafficSignObservationDto observationDto);
}
