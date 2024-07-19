package net.zecher.backend;

import net.zecher.backend.dto.TrafficSignObservationDto;
import net.zecher.backend.service.TrafficSignObservationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;

@Component
public class StartupRunner implements CommandLineRunner {

    private final TrafficSignObservationService observationService;

    public StartupRunner(TrafficSignObservationService observationService) {
        this.observationService = observationService;
    }

    @Override
    public void run(String... args) throws Exception {
        var csvFile = new File("sign_data.csv");
        if (!csvFile.exists()) return;
        var lines = Files.readAllLines(csvFile.toPath());
        for (String line : lines) {
            String[] split = line.split(",");
            var observation = new TrafficSignObservationDto();
            observation.setLatitude(Double.parseDouble(split[0]));
            observation.setLongitude(Double.parseDouble(split[1]));
            observation.setHeading(Integer.parseInt(split[2]));
            var type = ObservationType.parse(split[3]);
            observation.setType(type);
            if (split.length > 4) {
                observation.setValue(split[4]);
            }
            observationService.addTrafficSignObservation(observation);
        }
    }
}
