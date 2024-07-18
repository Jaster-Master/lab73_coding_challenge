package net.zecher.backend.dto;

import net.zecher.backend.ObservationTypeEnum;

import java.util.Objects;

public class ObservationClusterDto {

    private long clusterId;
    private ObservationTypeEnum type;
    private String value;
    private int radius;

    public ObservationClusterDto() {
    }

    public long getClusterId() {
        return clusterId;
    }

    public void setClusterId(long clusterId) {
        this.clusterId = clusterId;
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
        ObservationClusterDto that = (ObservationClusterDto) o;
        return clusterId == that.clusterId && radius == that.radius && type == that.type && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clusterId, type, value, radius);
    }
}
