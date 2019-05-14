package com.epam.couriers.entity;

import java.util.Objects;

public class Goods extends Entity {
    private GoodsEnum typeGoods;
    private User courier;
    private int orderId;


    public Goods() {
    }

    public Goods(int id, GoodsEnum typeGoods) {
        super(id);
        this.typeGoods = typeGoods;
    }

    public Goods(int id, GoodsEnum typeGoods, User courier, int orderId) {
        super(id);
        this.typeGoods = typeGoods;
        this.courier = courier;
        this.orderId = orderId;
    }

    public Goods(GoodsEnum typeGoods, int orderId) {
        this.typeGoods = typeGoods;
        this.orderId = orderId;
    }

    public GoodsEnum getTypeGoods() {
        return typeGoods;
    }

    public void setTypeGoods(GoodsEnum typeGoods) {
        this.typeGoods = typeGoods;
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
    public String toString() {
        return "Goods{" +
                "typeGoods=" + typeGoods +
                ", courier=" + courier +
                ", orderId=" + orderId +
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
        Goods goods = (Goods) o;
        return orderId == goods.orderId &&
                typeGoods == goods.typeGoods &&
                Objects.equals(courier, goods.courier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeGoods, courier, orderId);
    }
}
