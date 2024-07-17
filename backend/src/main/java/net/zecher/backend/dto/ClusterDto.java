package net.zecher.backend.dto;

import net.zecher.backend.ObservationType;

import java.util.Objects;

public class ClusterDto {

    private long clusterId;
    private ObservationType type;
    private String value;
    private int radius;

    public ClusterDto() {
    }

    public long getClusterId() {
        return clusterId;
    }

    public void setClusterId(long clusterId) {
        this.clusterId = clusterId;
    }

    public ObservationType getType() {
        return type;
    }

    public void setType(ObservationType type) {
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
        ClusterDto that = (ClusterDto) o;
        return clusterId == that.clusterId && radius == that.radius && type == that.type && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clusterId, type, value, radius);
    }
}
