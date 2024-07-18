package net.zecher.backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Observation_Type")
public class ObservationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OBSERVATION_TYPE_ID")
    private long observationTypeId;

    @Column(name = "OBSERVATION_TYPE")
    private String observationType;

    public long getObservationTypeId() {
        return observationTypeId;
    }

    public void setObservationTypeId(long observationTypeId) {
        this.observationTypeId = observationTypeId;
    }

    public String getObservationType() {
        return observationType;
    }

    public void setObservationType(String observationType) {
        this.observationType = observationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObservationType that = (ObservationType) o;
        return observationTypeId == that.observationTypeId && Objects.equals(observationType, that.observationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(observationTypeId, observationType);
    }
}
