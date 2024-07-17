package net.zecher.backend.controller;

import net.zecher.backend.dto.TrafficSignObservationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrafficSignObservationController {

    private static final String CONTROLLER_PATH = "/api/traffic-sign-observations";

    @GetMapping(CONTROLLER_PATH + "/{id}")
    private ResponseEntity<TrafficSignObservationDto> getTrafficSignObservation(@PathVariable("id") long id) {
        return ResponseEntity.ok(new TrafficSignObservationDto());
    }

    @GetMapping(CONTROLLER_PATH)
    private ResponseEntity<List<TrafficSignObservationDto>> getTrafficSignObservation() {
        return ResponseEntity.ok(List.of(new TrafficSignObservationDto()));
    }

    @PostMapping(CONTROLLER_PATH)
    private ResponseEntity<TrafficSignObservationDto> postTrafficSignObservation(TrafficSignObservationDto observationDto) {
        return ResponseEntity.ok(observationDto);
    }
}
