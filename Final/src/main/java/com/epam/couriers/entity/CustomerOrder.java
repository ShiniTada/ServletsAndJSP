package com.epam.couriers.entity;

import java.util.Objects;

public class CustomerOrder extends Entity {
    private String from;
    private String to;
    private String introductionDate;
    private StatusEnum status;
    private String goodsDescription;
    private User customer;
    private User courier;

    public CustomerOrder() {
    }

    public CustomerOrder(int id, String from, String to, String introductionDate, StatusEnum status, String goodsDescription, User customer, User courier) {
        super(id);
        this.from = from;
        this.to = to;
        this.introductionDate = introductionDate;
        this.status = status;
        this.goodsDescription = goodsDescription;
        this.customer = customer;
        this.courier = courier;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getIntroductionDate() {
        return introductionDate;
    }

    public void setIntroductionDate(String introductionDate) {
        this.introductionDate = introductionDate;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getCourier() {
        return courier;
    }

    public void setCourier(User courier) {
        this.courier = courier;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", introductionDate='" + introductionDate + '\'' +
                ", status=" + status +
                ", goodsDescription='" + goodsDescription + '\'' +
                ", customer=" + customer +
                ", courier=" + courier +
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
        CustomerOrder that = (CustomerOrder) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(to, that.to) &&
                Objects.equals(introductionDate, that.introductionDate) &&
                status == that.status &&
                Objects.equals(goodsDescription, that.goodsDescription) &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(courier, that.courier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, introductionDate, status, goodsDescription, customer, courier);
    }
}
