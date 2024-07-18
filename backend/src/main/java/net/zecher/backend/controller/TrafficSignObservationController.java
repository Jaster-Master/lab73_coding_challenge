package net.zecher.backend.controller;

import net.zecher.backend.ObservationTypeEnum;
import net.zecher.backend.dto.TrafficSignObservationDto;
import net.zecher.backend.service.TrafficSignObservationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private ResponseEntity<List<TrafficSignObservationDto>> getTrafficSignObservations(@RequestParam(value = "type", required = false) String type, @RequestParam(value = "value", required = false) String value) {
        ObservationTypeEnum typeEnum = ObservationTypeEnum.parse(type.toUpperCase());
        var result = observationService.getTrafficSignObservations(typeEnum, value);
        return ResponseEntity.ok(result);
    }

    @PostMapping(CONTROLLER_PATH)
    private ResponseEntity<TrafficSignObservationDto> postTrafficSignObservation(TrafficSignObservationDto observationDto) {
        var result = observationService.addTrafficSignObservation(observationDto);
        return ResponseEntity.ok(result);
    }
}
