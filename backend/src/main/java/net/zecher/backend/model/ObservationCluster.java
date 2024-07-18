package net.zecher.backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Observation_Cluster")
public class ObservationCluster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OBSERVATION_CLUSTER_ID")
    private long observationClusterId;

    @ManyToOne
    @JoinColumn(name = "OBSERVATION_TYPE_ID")
    private ObservationType observationType;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "RADIUS")
    private int radius;

    public long getObservationClusterId() {
        return observationClusterId;
    }

    public void setObservationClusterId(long observationClusterId) {
        this.observationClusterId = observationClusterId;
    }

    public ObservationType getObservationType() {
        return observationType;
    }

    public void setObservationType(ObservationType observationType) {
        this.observationType = observationType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObservationCluster that = (ObservationCluster) o;
        return observationClusterId == that.observationClusterId && radius == that.radius && Objects.equals(observationType, that.observationType) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(observationClusterId, observationType, value, radius);
    }
}
