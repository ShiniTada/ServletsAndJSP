package com.epam.couriers.entity;

import java.util.Objects;

public class Transport extends Entity {
    private TransportEnum typeTransport;
    private User courier;
    private int orderId;


    public Transport() {
    }


    public Transport(int id, TransportEnum typeTransport) {
        super(id);
        this.typeTransport = typeTransport;
    }

    public Transport(int id, TransportEnum typeTransport, User courier, int orderId) {
        super(id);
        this.typeTransport = typeTransport;
        this.courier = courier;
        this.orderId = orderId;
    }

    public Transport(TransportEnum typeTransport, int orderId) {
        this.typeTransport = typeTransport;
        this.orderId = orderId;
    }

    public TransportEnum getTypeTransport() {
        return typeTransport;
    }

    public void setTypeTransport(TransportEnum typeTransport) {
        this.typeTransport = typeTransport;
    }

    public User getCourier() {
        return courier;
    }

    public void setCourier(User courier) {
        this.courier = courier;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transport transport = (Transport) o;
        return orderId == transport.orderId &&
                typeTransport == transport.typeTransport &&
                Objects.equals(courier, transport.courier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeTransport, courier, orderId);
    }

    @Override
    public String toString() {
        return "Transport{" +
                "typeTransport=" + typeTransport +
                ", courier=" + courier +
                ", orderId=" + orderId +
                '}';
    }
}
