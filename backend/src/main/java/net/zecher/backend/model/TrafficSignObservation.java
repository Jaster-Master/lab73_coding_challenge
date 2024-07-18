package net.zecher.backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Traffic_Sign_Observation")
public class TrafficSignObservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAFFIC_SIGN_OBSERVATION_ID")
    private long trafficSignObservationId;

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "LONGITUDE")
    private double longitude;

    @Column(name = "HEADING")
    private int heading;

    @Column(name = "OBSERVATION_TYPE")
    private String observationType;

    @Column(name = "VALUE")
    private String value;

    public long getTrafficSignObservationId() {
        return trafficSignObservationId;
    }

    public void setTrafficSignObservationId(long trafficSignObservationId) {
        this.trafficSignObservationId = trafficSignObservationId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public String getObservationType() {
        return observationType;
    }

    public void setObservationType(String observationType) {
        this.observationType = observationType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrafficSignObservation that = (TrafficSignObservation) o;
        return trafficSignObservationId == that.trafficSignObservationId && Double.compare(latitude, that.latitude) == 0 && Double.compare(longitude, that.longitude) == 0 && heading == that.heading && Objects.equals(observationType, that.observationType) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trafficSignObservationId, latitude, longitude, heading, observationType, value);
    }
}
