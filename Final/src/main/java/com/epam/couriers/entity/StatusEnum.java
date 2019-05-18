package com.epam.couriers.entity;

public enum StatusEnum {
    POSTED("posted"),
    DELIVERED("delivered"),
    COMPLETED("completed"),
    DENIED("denied");
    private static final String UNDERSCORE = "_";
    private static final String DASH = "-";

    private String value;

    StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String toEnumFormat(String name) {
        return name.replaceAll(DASH, UNDERSCORE).toUpperCase();
    }

}
