package com.epam.couriers.entity;

public enum GoodsEnum {
    FOOD("food"),
    TECH("tech"),
    FURNITURE("furniture"),
    EASY_TO_BEAT("easy-to-beat"),
    ANOTHER("another");

    private String value;
    private static final String UNDERSCORE = "_";
    private static final String DASH = "-";

    GoodsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String toEnumFormat(String name) {
        return name.replaceAll(DASH, UNDERSCORE).toUpperCase();
    }

    public String toTag() {
        return name().replaceAll(UNDERSCORE, DASH).toLowerCase();
    }
}