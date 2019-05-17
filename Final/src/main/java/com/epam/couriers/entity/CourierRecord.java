package com.epam.couriers.entity;

import java.util.Objects;

public class CourierRecord extends Entity {
    private User courier;
    private double markQuality;
    private double markPoliteness;
    private double markPunctuality;
    private double markCommon;
    private int status;

    public CourierRecord(int id, User courier, double mark, int status) {
        super(id);
        this.courier = courier;
        this.markCommon = mark;
        this.status = status;
    }

    public CourierRecord(int id, User courier, double markQuality, double markPolitness, double markPunctuality, double markCommon, int status) {
        super(id);
        this.courier = courier;
        this.markQuality = markQuality;
        this.markPoliteness = markPolitness;
        this.markPunctuality = markPunctuality;
        this.markCommon = markCommon;
        this.status = status;
    }

    public CourierRecord() {
    }

    public double getMarkQuality() {
        return markQuality;
    }

    public void setMarkQuality(double markQuality) {
        this.markQuality = markQuality;
    }

    public double getMarkPoliteness() {
        return markPoliteness;
    }

    public void setMarkPoliteness(double markPoliteness) {
        this.markPoliteness = markPoliteness;
    }

    public double getMarkPunctuality() {
        return markPunctuality;
    }

    public void setMarkPunctuality(double markPunctuality) {
        this.markPunctuality = markPunctuality;
    }

    public User getCourier() {
        return courier;
    }

    public void setCourier(User courier) {
        this.courier = courier;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getMarkCommon() {
        return markCommon;
    }

    public void setMarkCommon(double markCommon) {
        this.markCommon = markCommon;
    }

    @Override
    public String toString() {
        return "CourierRecord{" +
                "courier=" + courier +
                ", markQuality=" + markQuality +
                ", markPoliteness=" + markPoliteness +
                ", markPunctuality=" + markPunctuality +
                ", markCommon=" + markCommon +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourierRecord that = (CourierRecord) o;
        return Double.compare(that.markQuality, markQuality) == 0 &&
                Double.compare(that.markPoliteness, markPoliteness) == 0 &&
                Double.compare(that.markPunctuality, markPunctuality) == 0 &&
                Double.compare(that.markCommon, markCommon) == 0 &&
                status == that.status &&
                Objects.equals(courier, that.courier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courier, markQuality, markPoliteness, markPunctuality, markCommon, status);
    }
}


