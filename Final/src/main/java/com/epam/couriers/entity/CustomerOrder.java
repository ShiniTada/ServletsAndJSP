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
    private int price;

    public CustomerOrder() {
    }

    public CustomerOrder(String from, String to, String introductionDate, StatusEnum status, String goodsDescription, User customer, User courier, int price) {
        this.from = from;
        this.to = to;
        this.introductionDate = introductionDate;
        this.status = status;
        this.goodsDescription = goodsDescription;
        this.customer = customer;
        this.courier = courier;
        this.price = price;
    }

    public CustomerOrder(int id, String from, String to, String introductionDate, StatusEnum status, String goodsDescription, User customer, User courier, int price) {
        super(id);
        this.from = from;
        this.to = to;
        this.introductionDate = introductionDate;
        this.status = status;
        this.goodsDescription = goodsDescription;
        this.customer = customer;
        this.courier = courier;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
                ", price=" + price +
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
        CustomerOrder order = (CustomerOrder) o;
        return price == order.price &&
                Objects.equals(from, order.from) &&
                Objects.equals(to, order.to) &&
                Objects.equals(introductionDate, order.introductionDate) &&
                status == order.status &&
                Objects.equals(goodsDescription, order.goodsDescription) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(courier, order.courier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, introductionDate, status, goodsDescription, customer, courier, price);
    }
}
