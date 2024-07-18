package net.zecher.backend.controller;

import net.zecher.backend.dto.TrafficSignObservationDto;
import net.zecher.backend.service.TrafficSignObservationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrafficSignObservationController {

    private static final String CONTROLLER_PATH = "/api/traffic-sign-observations";

    private final TrafficSignObservationServiceImpl observationService;

    public TrafficSignObservationController(TrafficSignObservationServiceImpl observationService) {
        this.observationService = observationService;
    }

    @GetMapping(CONTROLLER_PATH + "/{id}")
    private ResponseEntity<TrafficSignObservationDto> getTrafficSignObservation(@PathVariable("id") long id) {
        var result = observationService.getTrafficSignObservation(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping(CONTROLLER_PATH)
    private ResponseEntity<List<TrafficSignObservationDto>> getTrafficSignObservations() {
        return ResponseEntity.ok(List.of(new TrafficSignObservationDto()));
    }

    @PostMapping(CONTROLLER_PATH)
    private ResponseEntity<TrafficSignObservationDto> postTrafficSignObservation(TrafficSignObservationDto observationDto) {
        var result = observationService.addTrafficSignObservation(observationDto);
        return ResponseEntity.ok(result);
    }
}
