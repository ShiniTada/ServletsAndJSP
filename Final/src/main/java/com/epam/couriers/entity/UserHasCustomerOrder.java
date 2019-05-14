package com.epam.couriers.entity;

public class UserHasCustomerOrder extends Entity {
    private int userId;
    private int orderId;

    public UserHasCustomerOrder(int userId) {
    }

    public UserHasCustomerOrder(int id, int userId, int orderId) {
        super(id);
        this.userId = userId;
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "UserHasCustomerOrder{" +
                "userId=" + userId +
                ", orderId=" + orderId +
                '}';
    }
}
