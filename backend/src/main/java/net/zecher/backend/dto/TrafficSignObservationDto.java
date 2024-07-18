package net.zecher.backend.dto;

import net.zecher.backend.ObservationTypeEnum;

import java.util.Objects;

public class TrafficSignObservationDto {

    private long id;
    private double latitude;
    private double longitude;
    private int heading;
    private ObservationTypeEnum type;
    private String value;
    private long clusterId;

    public TrafficSignObservationDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public ObservationTypeEnum getType() {
        return type;
    }

    public void setType(ObservationTypeEnum type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getClusterId() {
        return clusterId;
    }

    public void setClusterId(long clusterId) {
        this.clusterId = clusterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrafficSignObservationDto that = (TrafficSignObservationDto) o;
        return id == that.id && Double.compare(latitude, that.latitude) == 0 && Double.compare(longitude, that.longitude) == 0 && heading == that.heading && clusterId == that.clusterId && type == that.type && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude, heading, type, value, clusterId);
    }
}
