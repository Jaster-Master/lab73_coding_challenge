package net.zecher.backend;

import net.zecher.backend.model.ObservationType;
import net.zecher.backend.repo.ObservationTypeRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final ObservationTypeRepo typeRepo;

    public StartupRunner(ObservationTypeRepo typeRepo) {
        this.typeRepo = typeRepo;
    }

    @Override
    public void run(String... args) {
        if (typeRepo.count() > 0) return;
        for (ObservationTypeEnum value : ObservationTypeEnum.values()) {
            var observationType = new ObservationType();
            observationType.setObservationType(value.name());
            typeRepo.save(observationType);
        }
    }
}
